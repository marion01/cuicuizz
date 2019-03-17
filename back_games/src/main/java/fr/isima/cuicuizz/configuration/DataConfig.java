package fr.isima.cuicuizz.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.sqlite.SQLiteDataSource;

/**
 * Configuration class for MyBatis Framework.
 * 
 *
 */
@Configuration
@MapperScan("fr.isima.cuicuizz.dbaccess.mybatis.dao")
public class DataConfig {

	/**
	 * data source definition, uses database realtive path in resources of project
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		final SQLiteDataSource dataSource = new SQLiteDataSource();
		String url = "jdbc:sqlite:" + ClassLoader.getSystemClassLoader().getResource("database.db").getFile();
		url = url.replace("/", "\\");
		dataSource.setUrl(url);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * session configuration
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("fr.isima.cuicuizz.model");
		return sessionFactory;
	}

}