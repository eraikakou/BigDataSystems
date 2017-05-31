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
