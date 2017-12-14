
package com.wpl.gift.utils;


import java.util.List;

import com.wpl.gift.model.RegistryItem;
import com.wpl.gift.model.User;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * Author Manohar
 */

public class MemCacheUtils	 {

	//private static final Logger logger = LogManager.getLogger(MemCacheUtil.class);
	private static MemCachedClient cacheClient = null;

	static {
		String[] server = { "localhost:11211" };
		SockIOPool sockPool = SockIOPool.getInstance("utdbids");
		sockPool.setServers(server);
		sockPool.setFailover(true);
		sockPool.setInitConn(10);
		sockPool.setMinConn(5);
		sockPool.setMaxConn(250);
		sockPool.setMaintSleep(30);
		sockPool.setNagle(false);
		sockPool.setSocketTO(3000);
		sockPool.setAliveCheck(true);
		sockPool.initialize();
		cacheClient = new MemCachedClient("utdbids");

	}

	public static boolean keepInCache(String key, List<RegistryItem> value) {
		System.out.println("cache out value");
		return cacheClient.set(key, value);
	}


	public static void storeInCache(int userId, User userDetails) {
		System.out.println("Cache Miss");

		System.out.println(userDetails.getFirstName());
		System.out.println(userDetails.getLastName());

		cacheClient.add(String.valueOf(userId), userDetails);
		//User test= (User) cacheClient.get(String.valueOf(userId));

		//System.out.println(test.getFirstName());
		//System.out.println(test.getLastName());

		//cacheClient.add(String.valueOf(userId), userDetails.getLastName());
	}

	public static Object getValuesFromCache(int userId) {
		User value = (User) cacheClient.get((String.valueOf(userId)));   //    (String.valueOf(userId));
		if (value == null) {
			System.out.println("Search Keyword Not Present- "+userId+" - Cache Miss");
			return null;

		} else {
			System.out.println("Search Keyword Present- "+userId+" Cache Hit");
			return value;
		}
	}
}