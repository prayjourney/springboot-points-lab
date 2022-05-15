package com.zgy.learn.elasticsearchfirstdemo;

import lombok.SneakyThrows;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RestHighLevelClientTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testConnection() {
        // ElasticsearchRestTemplate封装了RestHighLevelClient
        System.out.println(elasticsearchRestTemplate);
        System.out.println(restHighLevelClient);
    }

    /**
     * 创建索引, 并且新增doc
     */
    @SneakyThrows
    @Test
    public void createIndex() {
        IndexRequest indexRequest = new IndexRequest("hello");
        Map<String, Object> mp = new HashMap<>();
        mp.put("id", 2);
        mp.put("name", "lisi");
        mp.put("age", 22);
        indexRequest.source(mp);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 更新doc
     */
    @SneakyThrows
    @Test
    public void updateDoc() {
        UpdateRequest updateRequest = new UpdateRequest("hello", "iXacxoABUxjO4mzR2sQV");
        Map<String, Object> mp = new HashMap<>();
        mp.put("id", 2);
        mp.put("name", "www1");
        mp.put("age", 22);
        updateRequest.doc(mp);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除doc
     */
    @SneakyThrows
    @Test
    public void deleteDoc() {
        DeleteRequest deleteRequest = new DeleteRequest("hello", "TnbTxoABUxjO4mzRkep5");
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 获取doc
     */
    @SneakyThrows
    @Test
    public void getDoc() {
        GetRequest getRequest = new GetRequest("hello", "O3afxoABUxjO4mzRa8aS");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> sourceAsMap = response.getSourceAsMap();
        System.out.println(sourceAsMap);
    }

    /**
     * {
     *     "from":0,
     *     "size":10,
     *     "query":{
     *         "range":{
     *             "age":{
     *                 "from":10,
     *                 "to":20
     *             }
     *         }
     *     },
     *     "aggregations":{
     *         "avgBalance":{
     *             "avg":{
     *                 "field":"balance"
     *             }
     *         }
     *     }
     * }
     */
    // 复杂查询
    @SneakyThrows
    @Test
    public void testQueryAndAggregations() {
        // 1. 创建请求
        SearchRequest searchRequest = new SearchRequest();
        // 2. 指定索引
        searchRequest.indices("bank");
        // 3. 设置检索条件, DSL语句转化
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.rangeQuery("age").lte(20).gte(10));
        AggregationBuilder aggregationBuilder = AggregationBuilders.avg("avgBalance").field("balance");
        sourceBuilder.aggregation(aggregationBuilder);
        sourceBuilder.size(10);
        sourceBuilder.from(0);
        searchRequest.source(sourceBuilder);
        // 获取查询的语句
        System.out.println(sourceBuilder.toString());
        System.out.println(searchRequest.toString());
        // 4. 执行检索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 5. 分析结果
        SearchHits hits = searchResponse.getHits();
        Aggregations aggregations = searchResponse.getAggregations();
        // 打印搜索结果, 一般是多个
        for (SearchHit searchHit : hits) {
            System.out.println(searchHit.toString());
        }
        // 打印聚合结果
        Avg avgBalance = aggregations.get("avgBalance");
        System.out.println("平均工资是: " + avgBalance.value());
    }

}
