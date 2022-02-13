# connection pooling Project

> What is connection pooling?

Connection pooling means a pool of Connection Objects. Connection pooling is based on an object pool design pattern. Object pooling design pattern is
used when the cost (time & resources like CPU, Network, and IO) of creating new objects is higher. As per the Object pooling design pattern, the
application creates an object in advance and place them in Pool or Container. Whenever our application requires such objects, it acquires them from
the pool rather than creating a new one.

An application that uses a connection pooling strategy has already DB connection objects which can be reused. So, when there is a need to interact
with the database, the application obtains connection instances from Pool. Connection pooling improves application performance that interacts with the
database.

> **We can create our own implementations of Connection pooling. Any connection pooling framework needs to do three tasks.**

- Creating Connection Objects
- Manage usage of created Objects and validate them
- Release/Destroy Objects

> Connection Pooling in Java Application

Let’s have a look at below libraries:

- Apache Commons DBCP 2
- HikariCP
- C3P0

---------

### Apache commons DBCP 2:

```
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-dbcp2</artifactId>
      <version>2.7.0</version>
    </dependency>
```

### Apache DBCP 2.0 provides two types of DataSource:

1. BasicDataSource
2. PoolingDataSource

**BasicDataSource:**
<p>As the name suggests, it is simple and suitable for most common use cases. It internally creates PoolingDataSource for us.</p>
Let’s have a look at below steps to initialize connection pool.

1. Create an instance of BasicDataSource
2. Specify JDBC Url, database username and password
3. Specify the minimum number of idle connection ( Minimum number of connections that needs to remain in the pool at any time)
4. Specify the maximum number of idle connection (Maximum number of Idle connection in the pool)
5. Specify the total number of maximum connections.

```
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(props.getProperty("DB_URL"));
        dataSource.setUsername(props.getProperty("DB_USERNAME"));
        dataSource.setPassword(props.getProperty("DB_PASSWORD"));
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        dataSource.getConnection();
```

**PoolingDataSource:**

<p>It offers more flexibility.</p>

1. Create an instance of ConnectionFactory using JDBC URL.

2. Create an instance of PoolableConnectionFactory using an instance of ConnectionFactory which was created in step 1

3. Create an instance of GenericObjectPoolConfig and set maximum idle, minimum idle and maximum connection properties

4. Now initialize ObjectPool using instances created in step 2 and step 3

5. Now set pool as an instance of PoolableConnectionFactory

6. Finally, initialize an instance of DataSource

```
        DataSource dataSource = null;
        props.setProperty("user",username"));
        props.setProperty("password","password");
        
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(props.getProperty("DB_URL"), props);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
        
        config.setMaxTotal(25);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
        poolableConnectionFactory.setPool(connectionPool);
        dataSource = new PoolingDataSource<>(connectionPool);
        dataSource.getConnectionFromPoolingDataSource();
```

---
