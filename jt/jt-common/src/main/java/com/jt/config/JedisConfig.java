package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class JedisConfig {
    @Value("${redis.clusters}")
    private String clusters;

    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodes=new HashSet<>();
        String[] nodesArray=clusters.split(",");
        for (String node:nodesArray
             ) {
            String host=node.split(":")[0];
            int port=Integer.parseInt(node.split(":")[1]);
            HostAndPort hostAndPort=new HostAndPort(host, port);
            nodes.add(hostAndPort);
        }
        return new JedisCluster(nodes);
    }



















   /* @Value("${redis.nodes}")
    private String nodes;  //node,node,node.....*/

    //配置redis分片机制
    /*@Bean
    public ShardedJedis shardedJedis(){
        nodes = nodes.trim();   //去除两边多余的空格
        List<JedisShardInfo> shards = new ArrayList<>();
        String[] nodeArray = nodes.split(",");
        for (String strNode : nodeArray){   //strNode = host:port
            String host = strNode.split(":")[0];
            int port = Integer.parseInt(strNode.split(":")[1]);
            JedisShardInfo info = new JedisShardInfo(host, port);
            shards.add(info);
        }
        return new ShardedJedis(shards);
    }*/




   /* @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @Bean
    public Jedis jedis(){

        return new Jedis(host,port);
    }*/
}
