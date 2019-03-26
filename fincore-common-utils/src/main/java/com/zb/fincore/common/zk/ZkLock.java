package com.zb.fincore.common.zk;

//import org.apache.commons.lang3.StringUtils;
//import org.apache.zookeeper.*;
//import org.apache.zookeeper.data.ACL;
//import org.apache.zookeeper.data.Stat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.util.Collections;
//import java.util.Enumeration;

/**
 * 功能: 基于Zookeeper的分布式锁
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2017/7/15 0015 10:45
 * 版本: V1.0
 */
public class ZkLock {

//    /**
//     * 日志器
//     */
//    private static final Logger logger = LoggerFactory.getLogger(ZkLock.class);
//
//    /**
//     * 锁定根节点名称
//     */
//    private static final String ROOT = "/fincore";
//
//    /**
//     * 锁的临时节点
//     **/
//    private static final String LOCK = "/lock";
//
//    /**
//     * ZK连接地址
//     */
//    private String zookeeper;
//
//    /**
//     * 锁定节点
//     */
//    private String lockNode;
//
//    /**
//     * zookeeper
//     */
//    private ZooKeeper zk;
//
//    private ZooKeeper getZookeeper() {
//        try {
//            ZooKeeper zk = new ZooKeeper(zookeeper, 5000, new Watcher() {
//                public void process(WatchedEvent event) {
//                    //节点的事件处理. you can do something when the node's data change
//                    //System.out.println("event " + event.getType() + " has happened!");
//                }
//            });
//            return zk;
//        } catch (IOException e) {
//            logger.error("连接zookeeper异常", e);
//        }
//        return null;
//    }
//
//    private String getLockPath(String lockLeafNode) {
//        String lockPath = ROOT;
//        if (StringUtils.isNotBlank(this.lockNode)) {
//            if (lockNode.startsWith("/")) {
//                lockPath = lockPath + lockNode;
//            } else {
//                lockPath = lockPath + "/" + lockNode;
//            }
//        } else {
//            lockPath = lockPath + LOCK;
//        }
//        if (lockLeafNode.startsWith("/")) {
//            lockPath = lockPath + lockLeafNode;
//        } else {
//            lockPath = lockPath + "/" + lockLeafNode;
//        }
//        return lockPath;
//    }
//
//    private String getLocalIpAddress() throws Exception {
//        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//        InetAddress ip = null;
//        while (allNetInterfaces.hasMoreElements()) {
//            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//            Enumeration addresses = netInterface.getInetAddresses();
//            while (addresses.hasMoreElements()) {
//                ip = (InetAddress) addresses.nextElement();
//                if (ip != null && ip instanceof Inet4Address) {
//                    logger.debug("本机的IP:{}", ip.getHostAddress());
//                    return ip.getHostAddress();
//                }
//            }
//        }
//        return null;
//    }
//
//    public boolean lock(String lockLeafNode) throws Exception {
//        String ipAddress = this.getLocalIpAddress();
//        return tryLock(lockLeafNode, ipAddress);
//    }
//
//    private boolean tryLock(String lockLeafNode, String lockValue) throws Exception {
//        String lockPath = this.getLockPath(lockLeafNode);
//        logger.debug("尝试锁定路径:{},{}", lockPath, lockValue);
//        if (zk == null) {
//            zk = this.getZookeeper();
//        }
//        try {
//            Stat stat = zk.exists(lockPath, true);
//            if (stat != null) {
//                byte[] buffer = zk.getData(lockPath, null, stat);
//                if (buffer == null) {
//                    logger.debug("路径:{},已经被锁定", lockPath);
//                } else {
//                    logger.debug("路径:{},已经被键值{}锁定", lockPath, new String(buffer, "UTF-8"));
//                }
//            } else {
//                zk.create(lockPath, lockValue.getBytes(), Collections.singletonList(new ACL(ZooDefs.Perms.ALL, ZooDefs.Ids.ANYONE_ID_UNSAFE)), CreateMode.EPHEMERAL);
//                logger.debug("路径:{},{}锁定成功", lockPath, lockValue);
//                return Boolean.TRUE;
//            }
//        } catch (Exception e) {
//            logger.error("尝试锁定路径:{},{}失败", lockPath, lockValue);
//            logger.error(e.getMessage(), e);
//        }
//        return Boolean.FALSE;
//    }
//
//    private boolean tryUnLock(String lockLeafNode, String lockValue) throws Exception {
//        String lockPath = this.getLockPath(lockLeafNode);
//        logger.debug("尝试解锁路径:{},{}", lockPath, lockValue);
//        if (zk == null) {
//            zk = this.getZookeeper();
//        }
//        try {
//            Stat stat = zk.exists(lockPath, true);
//            if (stat != null) {
//                byte[] buffer = zk.getData(lockPath, null, stat);
//                if (buffer == null) {
//                    zk.delete(lockPath, -1);
//                    logger.debug("路径:{},解锁成功", lockPath);
//                } else {
//                    String lockedValue = new String(buffer, "UTF-8");
//                    if (lockedValue.equals(lockValue)) {
//                        zk.delete(lockPath, -1);
//                        logger.debug("路径:{},{}解锁成功", lockPath, lockValue);
//                    } else {
//                        logger.debug("路径:{},{},{}键值匹配不成功,解锁失败", lockPath, lockValue, lockedValue);
//                        return Boolean.FALSE;
//                    }
//                }
//            } else {
//                logger.debug("路径:{},{}已经不存在,解锁成功", lockPath, lockValue);
//            }
//            return Boolean.TRUE;
//        } catch (Exception e) {
//            logger.error("尝试解锁路径:{},{}失败", lockPath, lockValue);
//            logger.error(e.getMessage(), e);
//        }
//        return Boolean.FALSE;
//    }
//
//    public boolean unlock(String lockLeafNode) throws Exception {
//        String ipAddress = this.getLocalIpAddress();
//        return tryUnLock(lockLeafNode, ipAddress);
//    }
//
//    public void close() throws Exception {
//        if (zk != null) {
//            zk.close();
//        }
//    }
//
//    public void setZookeeper(String zookeeper) {
//        this.zookeeper = zookeeper;
//    }
//
//    public String getLockNode() {
//        return lockNode;
//    }
//
//    public void setLockNode(String lockNode) {
//        this.lockNode = lockNode;
//    }
}
