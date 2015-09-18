package vic.test.infinispan;

import java.util.Map;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

public class Client {

	public static void main(String[] args) {
		// init
		RemoteCacheManager cacheManager = new RemoteCacheManager("localhost:11222;localhost:11322");
		RemoteCache<String, String> cache = cacheManager.getCache();
		
//		QueryFactory<Query> query = Search.getQueryFactory(cache);
//		testWrite(cache, 10000); // 8 sec
		testWrite(cache, 100000);
		
		long start = System.currentTimeMillis();
		cache.keySet();
		cache.keySet();
		long end = System.currentTimeMillis();
		System.out.println("-- 100000 time cost : " + (end - start));
		//printCachedData(cache);
		
		cacheManager.stop();
	}
	
	private static void testWrite(RemoteCache<String, String> cache, int count) {
		long start = System.currentTimeMillis();
		writeCache(cache, count);
		long end = System.currentTimeMillis();
		System.out.println("-- " + count + " time cost : " + (end - start));
	}

	private static void writeCache(RemoteCache<String, String> cache, int count) {
		for (int i = 0; i < count; i++) {
			String key = "testKey_" + i;
			String value = "tVal_" + i;
//			System.out.println("putting " + i);
			cache.putIfAbsent(key, value);
		}
	}
	
	private static void printCachedData(RemoteCache<String, String> cache) {
		long start = System.currentTimeMillis();
		Map<String, String> cachedData = cache.getBulk();
		for (Map.Entry<String, String> e : cachedData.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		long end = System.currentTimeMillis();
		System.out.println("-- " + cache.size() + " time cost : " + (end - start));
	}

}

