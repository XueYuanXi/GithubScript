/**
 * Copyright © 2023 All rights reserved.
 * 
 * @Description:
 * @date: 2023-02-27 10:27:31
 */
package com.xyx.boot.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import javax.annotation.Resource;

/**
 * Copyright: Copyright (c) 2023
 * ClassName: MongoDBConfig.java
 *
 * @version 1
 * @author wufeng
 * @since 2023-02-27 10:27:31
 */
@Configuration
public class MongoDBConfig {
	
	@Resource
	MongoDatabaseFactory mongoDatabaseFactory;
 
	@Bean
	GridFSBucket getGridFSBucket() {
		return GridFSBuckets.create(mongoDatabaseFactory.getMongoDatabase());
	}
		
	public MongoCollection<Document> getFileCollection(){
		MongoDatabase db = mongoDatabaseFactory.getMongoDatabase();
		return db.getCollection("fs.files");
	}
		
	public MongoCollection<Document> getChunksCollection(){
		MongoDatabase db = mongoDatabaseFactory.getMongoDatabase();
		return db.getCollection("fs.chunks");
	}
}
