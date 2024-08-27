package com.batchexample.bootbatch.cconfg;

import com.batchexample.bootbatch.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotficationImpl listener, Step step) {
        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(step)
                .build();
    }


    @Bean
    public Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                     ItemReader<Product> reader, ItemProcessor<Product, Product> itemProcessor,
                     ItemWriter<Product> writer) {
        return new StepBuilder("jobStep", jobRepository)
                .<Product, Product>chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public ItemWriter<Product> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Product>()
                .sql("INSERT INTO PRODUCTTS (product_id, title, description, price, discount, discounted_price) VALUES (:productId, :title, :description, :price, :discount, :discountedPrice)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("itemReader")
                .resource(new ClassPathResource("product.csv"))
                .linesToSkip(1)
                .delimited()
                .names("productId", "title", "description", "price", "discount", "discountedPrice")
                .targetType(Product.class)
                .build();
    }

    @Bean
    public ItemProcessor<Product, Product> itemProcessor() {
        return new CustomeItemProcessor();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
