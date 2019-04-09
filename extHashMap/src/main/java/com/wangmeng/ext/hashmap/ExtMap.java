package com.wangmeng.ext.hashmap;

/**
 * 纯手写Map接口<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
public interface ExtMap<K, V> {

	// 向集合中插入数据
	public V put(K k, V v);

	// 根据k 从Map集合中查询元素
	public V get(K k);

	// 获取集合元素个数
	public int size();

	// Entry的作用=== Node节点
	interface Entry<K, V> {
		K getKey();

		V getValue();

		V setValue(V value);
	}

}
