package org.platform.modules.bootstrap.config.ds;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class DataSourceContextHolder {

	private static final ThreadLocal<String> context = new ThreadLocal<String>();
	
	private static List<String> masterDataSources = new ArrayList<String>();
	private static List<String> slaveDataSources = new ArrayList<String>();
	
	private static Random random = new Random();

    public static void setDataSource(String value) {
        if (StringUtils.isBlank(value)) value = DataSource.MASTER;
    	if (DataSource.SLAVE.equals(value)) value = obtainSlaveDataSource();
        context.set(value);
    }

    public static String getDataSource() {
        return null == context.get() ? DataSource.MASTER : context.get();
    }

    public static void clearDataSource() {
        context.remove();
    }
    
    public static void addDataSource(Set<Object> dataSources){
    	if (null == dataSources || dataSources.isEmpty()) return;
    	Iterator<Object> dataSourceIterator = dataSources.iterator();
    	while (dataSourceIterator.hasNext()) {
    	    String dataSource = String.valueOf(dataSourceIterator.next());
            if (dataSource.startsWith("master")) {
                masterDataSources.add(dataSource);
            } else if (dataSource.startsWith("slave")) {
                slaveDataSources.add(dataSource);
            }
    	}
    }
    
    public static boolean containDataSource(String dataSource){
        return masterDataSources.contains(dataSource) || slaveDataSources.contains(dataSource);
    }
    
    public static void removeDataSource(String dataSource){
        masterDataSources.remove(dataSource);
        slaveDataSources.remove(dataSource);
    }
    
    private static String obtainSlaveDataSource() {
    	if (slaveDataSources.isEmpty()) return DataSource.MASTER;
    	int len = slaveDataSources.size();
    	return slaveDataSources.get(len == 1 ? 0 : random.nextInt(len));
    }
    
}