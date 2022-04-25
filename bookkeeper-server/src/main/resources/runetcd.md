./etcd \
--advertise-client-urls=http://192.168.31.90:2279 \
--data-dir=/tmp/dev/bk \
--initial-advertise-peer-urls=http://192.168.31.90:2280 \
--initial-cluster=http://192.168.31.90:2279 \
--listen-client-urls=http://127.0.0.1:2279,http://192.168.31.90:2279 \
--listen-metrics-urls=http://127.0.0.1:2281 \
--listen-peer-urls=http://192.168.31.90:2280 \
--name=lan \
--snapshot-count=10000





./etcd --advertise-client-urls=http://192.168.31.90:2279 --data-dir=/tmp/dev/bk --initial-advertise-peer-urls=http://192.168.31.90:2280 --initial-cluster=lan --listen-client-urls=http://127.0.0.1:2279,http://192.168.31.90:2279 --listen-metrics-urls=http://127.0.0.1:2281 --listen-peer-urls=http://192.168.31.90:2280 --name=lan --snapshot-count=10000






./etcd \
--advertise-client-urls=http://192.168.31.90:2279 \
--data-dir=/tmp/dev/bk \
--initial-advertise-peer-urls=http://192.168.31.90:2280 \
--initial-cluster=yunhorn=http://192.168.31.90:2280 \
--listen-client-urls=http://127.0.0.1:2279,http://192.168.31.90:2279 \
--listen-metrics-urls=http://127.0.0.1:2281 \
--listen-peer-urls=http://192.168.31.90:2280 \
--name=yunhorn \
--snapshot-count=10000
