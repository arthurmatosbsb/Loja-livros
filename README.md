# Loja-livros
Loja livros desenvolvida com EJB, CDI, JPA, Hibernate e PrimeFaces

Para compilar a aplicação adicione a configuração ao wildfly:

               <datasource jndi-name="java:/livros" pool-name="livros" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/livros</connection-url>
                    <driver>com.mysql</driver>
                    <pool>
                        <min-pool-size>10</min-pool-size>
                        <max-pool-size>100</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>root</user-name>
                        <password></password>
                    </security>


                   <driver name="com.mysql" module="com.mysql">
                        <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                    </driver>
