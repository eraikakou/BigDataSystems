# Apache Flink

Cluster Setup
------------------
This guide provides instructions on how to run Flink in a fully distributed fashion on a static (but possibly heterogeneous) cluster.

**Requirements :**

* **1. Software Requirements**

Flink runs on all UNIX-like environments, e.g. Linux, Mac OS X, and Cygwin (for Windows) and expects the cluster to consist of one master node and one or more worker nodes. Before you start to setup the system, make sure you have the following software installed on each node:

* **Java 1.7.x or higher**,
* **ssh** (sshd must be running to use the Flink scripts that manage remote components)

If your cluster does not fulfill these software requirements you will need to install/upgrade it.

* **2. JAVA_HOME Configuration**

Flink requires the JAVA_HOME environment variable to be set on the master and all worker nodes and point to the directory of your Java installation.

**Flink Setup :**

Go to the [downloads page](http://flink.apache.org/downloads.html) and get the ready to run package. Make sure to pick the Flink package matching your Hadoop version. If you don’t plan to use Hadoop, pick any version.
After downloading the latest release, copy the archive to your master node and extract it:

For Hadoop 2.7 we have the following :

```
cd /opt/
wget http://apache.forthnet.gr/flink/flink-1.2.1/flink-1.2.1-bin-hadoop27-scala_2.10.tgz

tar xzf flink-1.2.1-bin-hadoop27-scala_2.10.tgz
cd flink-1.2.1
```

After having extracted the system files, you need to configure Flink for the cluster by editing conf/flink-conf.yaml.
```
vi conf/flink-conf.yaml
```
Set the **jobmanager.rpc.address** key to point to your master node **s01**. Furthermode define the maximum amount of main memory the JVM is allowed to allocate on each node by setting the **jobmanager.heap.mb** and **taskmanager.heap.mb** keys.
The value is given in MB. If some worker nodes have more main memory which you want to allocate to the Flink system you can overwrite the default value by setting an environment variable FLINK_TM_HEAP on the respective node.

Finally you must provide a list of all nodes in your cluster which shall be used as worker nodes.
Therefore, similar to the HDFS configuration, edit the file conf/slaves and enter the IP/host name of each worker node. Each worker node will later run a TaskManager.

```
vi conf/slaves
```
and add (remove localhost from list if there)
```
s02
s03
```
The Flink directory must be available on every worker under the same path. You can use a shared NSF directory, or copy the entire Flink directory to every worker node.
```
scp -rp /opt/flink-1.2.1 s02:/opt
scp -rp /opt/flink-1.2.1 s03:/opt
```
Please see the [configuration page](https://ci.apache.org/projects/flink/flink-docs-release-1.0/setup/config.html) for details and additional configuration options.
In particular,

* the amount of available memory per TaskManager (taskmanager.heap.mb),
* the number of available CPUs per machine (taskmanager.numberOfTaskSlots),
* the total number of CPUs in the cluster (parallelism.default) and
* the temporary directories (taskmanager.tmp.dirs)
are very important configuration values.

**Starting Flink**

The following script starts a JobManager on the local node and connects via SSH to all worker nodes listed in the slaves file to start the TaskManager on each node. Now your Flink system is up and running. The JobManager running on the local node will now accept jobs at the configured RPC port.

Assuming that you are on the master node and inside the Flink directory:
To stop Flink, there is also a stop-cluster.sh script.

```
bin/start-cluster.sh
bin/stop-cluster.sh
```

