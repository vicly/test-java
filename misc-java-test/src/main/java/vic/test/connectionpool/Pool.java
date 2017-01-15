package vic.test.connectionpool;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * @author Vic Liu
 */
public interface Pool {

    /**
     * borrow connection from pool
     *
     * @throws SQLException if cannot borrow
     */
    Connection borrowConnection() throws SQLException;

    void returnConnection(Connection connection);

}


class PoolImpl implements Pool {

    private static final int DEFAULT_SIZE = 10;

    private long timeout = 2000; // 2 seconds

    private Object lock = new Object();

    private List<PooledConntion> pooledConntions = new ArrayList<>();

    PoolImpl(Supplier<Connection> connectionSupplier, int size) {
        initPool(size > 0 ? size : DEFAULT_SIZE);
    }

    private final void initPool(int size) {

    }

    @Override
    public Connection borrowConnection() throws SQLException {
        synchronized (lock) { // lock is the monitor
            long start = System.currentTimeMillis();
            Optional<PooledConntion> conn = anyUnborrowed();
            try {
                while (!conn.isPresent()) {
                    this.lock.wait(100);
                    long duration = System.currentTimeMillis() - start;
                    if (duration > timeout) {
                        throw new SQLException("Failed to borrow connection, timeout");
                    }
                    conn = anyUnborrowed();
                }
                PooledConntion connection = conn.get();
                connection.setBorrowed(true);
                return connection;
            } catch (InterruptedException e) {
                throw new SQLException("Failed to borrow connection, current thread is interrupted", e);
            }
        }
    }

    private Optional<PooledConntion> anyUnborrowed() {
        return this.pooledConntions.stream().filter(c -> c.isBorrowed()).findAny();
    }

    @Override
    public void returnConnection(Connection connection) {
        if (connection instanceof PooledConntion) {
            synchronized (lock) {
                PooledConntion pooledConntion = (PooledConntion) connection;
                pooledConntion.setBorrowed(false);
            }
        } else {
            throw new IllegalArgumentException("Wrong type connection");
        }
    }
}

class ConnectionDecorator implements Connection {
    private Connection wrapped;

    ConnectionDecorator(Connection wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);
    }

    Connection getWrapped() {
        return this.wrapped;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return this.wrapped.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.wrapped.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return null;
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return null;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {

    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        this.wrapped.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.wrapped.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {

    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return false;
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {

    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {

    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {

    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

    }

    @Override
    public void setHoldability(int holdability) throws SQLException {

    }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return null;
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {

    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    @Override
    public void setSchema(String schema) throws SQLException {

    }

    @Override
    public String getSchema() throws SQLException {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {

    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}

class PooledConntion extends ConnectionDecorator {
    private Pool pool;
    private boolean borrowed = false;
    private boolean closed = false;

    PooledConntion(Pool pool, Connection connection) {
        super(connection);
        this.pool = pool;
    }

    boolean isBorrowed() {
        return borrowed;
    }

    void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void close() {
        pool.returnConnection(this);
    }

}