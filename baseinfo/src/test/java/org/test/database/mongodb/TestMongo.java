package org.test.database.mongodb;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by 陆华 on 15-12-23 下午4:16
 */
public class TestMongo {
    @Test
    public void test() {
        ApplicationContext acontext = new ClassPathXmlApplicationContext("applicationContext-mongo.xml");
        MongoTemplate template = (MongoTemplate)acontext.getBean("mongoTemplate");
        if (!template.collectionExists(Book.class)) {
            template.createCollection(Book.class);
        }
        int i = 0;
        while (true) {
            Book book = new Book();
            i++;
            book.setBookName("虚空之遗" + i);
            book.setBookPrice(32.2 + i);
            template.insert(book);
            System.out.println(i + "===================");
        }
    }
}
