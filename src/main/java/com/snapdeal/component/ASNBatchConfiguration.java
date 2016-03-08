//package com.snapdeal.component;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//
//import com.snapdeal.dto.ASNMapper;
//import com.snapdeal.entity.ASN;
//
//@Configuration
//@EnableBatchProcessing
//public class ASNBatchConfiguration {
//
//    @Bean
//    public ItemReader<ASNMapper> reader() {
//        FlatFileItemReader<ASNMapper> reader = new FlatFileItemReader<ASNMapper>();
//        reader.setResource(file);
//        reader.setLineMapper(new DefaultLineMapper<ASNMapper>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "firstName", "lastName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<ASNMapper>() {{
//                setTargetType(ASNMapper.class);
//            }});
//        }});
//        return reader;
//    }
//
//    @Bean
//    public ItemProcessor<ASNMapper, ASN> processor() {
//        return new ASNItemProcessor();
//    }
//
//    @Bean
//    public ItemWriter<ASNMapper> writer(DataSource dataSource) {
//        JdbcBatchItemWriter<ASNMapper> writer = new JdbcBatchItemWriter<ASNMapper>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
//        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
//        writer.setDataSource(dataSource);
//        return writer;
//    }
//    // end::readerwriterprocessor[]
//
//    // tag::jobstep[]
//    @Bean
//    public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
//        return jobs.get("importUserJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(s1)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<ASNMapper> reader,
//            ItemWriter<ASN> writer, ItemProcessor<ASNMapper, ASN> processor) {
//        return stepBuilderFactory.get("step1")
//                .<ASNMapper, ASN> chunk(10)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//    // end::jobstep[]
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}