# SpringBootManyDB
Spring  Boot 多数据源处理方式
只要使用多数据源情况，进行整合数据源使用mybatis 进行多数据源映射。
对每一个数据源需要进行数据配置，开启数据源的定义，事务等。

多数据源进行事务管理。添加@Transactional(value = "firstTransactionManager") //开启事务管理
value 进行指定数据源。进行事务控制。
