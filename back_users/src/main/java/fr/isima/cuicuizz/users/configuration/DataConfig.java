package fr.isima.cuicuizz.users.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.sqlite.SQLiteDataSource;

/**
 * Class to configure MyBatis framework
 *
 */
@Configuration
@MapperScan("fr.isima.cuicuizz.users.dbaccess.mybatis.dao")
public class DataConfig {

	/**
	 * Launch the sql data base
	 */
	@Bean
	public DataSource dataSource() {
		final SQLiteDataSource dataSource = new SQLiteDataSource();
		String url = "jdbc:sqlite:" + ClassLoader.getSystemClassLoader().getResource("database.db").getFile();
		url = url.replace("/", "\\");
		dataSource.setUrl(url);
		return dataSource;
	}

	/**
	 * Set the Transaction Manager
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * Set a SQL session for the transactions
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("fr.isima.cuicuizz.users.model");
		return sessionFactory;
	}
}