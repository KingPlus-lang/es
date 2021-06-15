package com.wjf.elasticsearch.demo;

import com.alibaba.fastjson.JSON;
import com.wjf.elasticsearch.demo.entity.NewCar;
import com.wjf.elasticsearch.demo.entity.Person;
import com.wjf.elasticsearch.demo.mapper.NewCarMapper;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private NewCarMapper newCarMapper;

    @Test
    void contextLoads() {
//        //创建ES客户端
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
//                new HttpHost("10.0.0.11",9200,"http")
//        ));
        System.out.println(client);
    }

    /**
     * 添加索引
     *
     * @author KingPlus
     * @date 2021/6/13  12:18
     */
    @Test
    public void addIndex() throws IOException {
        //1.使用client获取操作索引的对象
        IndicesClient indicesClient = client.indices();
        //2.具体操作，获取返回值
        CreateIndexRequest createRequest = new CreateIndexRequest("wjf");
        CreateIndexResponse response = indicesClient.create(createRequest, RequestOptions.DEFAULT);
        //3.根据返回值判断结果
        System.out.println(response.isAcknowledged());
    }

    /**
     * 查询索引
     *
     * @author KingPlus
     * @date 2021/6/14  10:55
     */
    @Test
    public void queryIndex() throws IOException {
        IndicesClient indices = client.indices();
        org.elasticsearch.client.indices.GetIndexRequest indexRequest = new org.elasticsearch.client.indices.GetIndexRequest("person");
        GetIndexResponse getIndexResponse = indices.get(indexRequest, RequestOptions.DEFAULT);

        Map<String, MappingMetaData> mappings = getIndexResponse.getMappings();
        for (String i : mappings.keySet()) {
            System.out.println(i + ":" + mappings.get(i));
        }
    }

    /**
     * 删除索引
     *
     * @author KingPlus
     * @date 2021/6/14  16:42
     */
    @Test
    public void deleteIndex() throws IOException {
        IndicesClient indices = client.indices();

        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("wjf");
        AcknowledgedResponse delete = indices.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 判断索引是否存在
     *
     * @author KingPlus
     * @date 2021/6/14  17:04
     */

    @Test
    public void existIndex() throws IOException {
        IndicesClient indices = client.indices();

        GetIndexRequest getRequest = new GetIndexRequest("wjf");
        boolean exists = indices.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    /**
     * 添加文档，使用map
     *
     * @author KingPlus
     * @date 2021/6/14  17:05
     */
    @Test
    public void addDoc() throws IOException {
        //数据对象，map
        Map data = new HashMap<>();
        data.put("address", "北京昌平");
        data.put("name", "马六");
        data.put("age", 25);
        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("person").id("1").source(data);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }

    /**
     * 添加文档，使用对象作为数据
     *
     * @author KingPlus
     * @date 2021/6/14  17:05
     */
    @Test
    public void addDoc2() throws IOException {
        //数据对象
        Person person = new Person();
        person.setId("2");
        person.setName("王七");
        person.setAddress("河北廊坊");
        person.setAge(22);

        //将对象转为jason
        String data = JSON.toJSONString(person);
        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("person").id(person.getId()).source(data, XContentType.JSON);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }


    /**
     * 修改文档，使用对象作为数据
     *
     * @author KingPlus
     * @date 2021/6/14  17:05
     */
    @Test
    public void updateDoc() throws IOException {
        //数据对象
        Person person = new Person();
        person.setId("2");
        person.setName("王九");
        person.setAddress("河北廊坊");
        person.setAge(22);

        //将对象转为jason
        String data = JSON.toJSONString(person);
        //1.获取操作文档的对象
        IndexRequest request = new IndexRequest("person").id(person.getId()).source(data, XContentType.JSON);
        //添加数据，获取结果
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        //打印响应结果
        System.out.println(response.getId());
    }

    /**
     * 查询文档
     *
     * @author KingPlus
     * @date 2021/6/14  17:27
     */

    @Test
    public void findDocById() throws IOException {
        GetRequest getRequest = new GetRequest("person", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());

    }


    @Test
    public void delDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("person", "2");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());
    }

    //================================================================================
    //批量操作

    @Test
    public void testBulk() throws IOException {
        //创建bulkRequest对象，整合所有操作
        BulkRequest bulkRequest = new BulkRequest();

        //删除1号记录
        DeleteRequest deleRequest = new DeleteRequest("person", "1");
        bulkRequest.add(deleRequest);
        //添加3号记录
        Map data = new HashMap();
        data.put("name","李华");
        data.put("age",55);
        data.put("address","内蒙古呼市");
        IndexRequest addRequest = new IndexRequest("person").id("3").source(data);
        bulkRequest.add(addRequest);

        //修改2号记录
        Map map = new HashMap();
        map.put("name","霍元甲");
        UpdateRequest updateRequest = new UpdateRequest("person","2").doc(map);
        bulkRequest.add(updateRequest);

        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        RestStatus status = bulk.status();
        System.out.println(status);
    }

    /**批量导入
     * @author KingPlus
     * @date 2021/6/14  23:16
     */
    @Test
    public void importData() throws IOException {
        List<NewCar> cars = newCarMapper.findAll();
        int i =0;

        //bulk导入
        BulkRequest bulkRequest = new BulkRequest();
        //循环cars，创建IndexRequest添加数据
        for(NewCar car:cars){
            IndexRequest indexRequest = new IndexRequest("newcar");
            indexRequest.id(++i+"").source(JSON.toJSONString(car),XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        client.bulk(bulkRequest,RequestOptions.DEFAULT);
    }



}
