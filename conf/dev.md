
~/server/etcd-v3.5.1-linux-amd64


./etcdctl --endpoints=http://192.168.31.90:2279 get /ledgers/bookies



./etcd --advertise-client-urls=http://192.168.31.90:2279 --data-dir=/home/oem/server/dev/etcdbk --initial-advertise-peer-urls=http://192.168.31.90:2280 --initial-cluster=yunhorn=http://192.168.31.90:2280 --listen-client-urls=http://127.0.0.1:2279,http://192.168.31.90:2279 --listen-metrics-urls=http://127.0.0.1:2281 --listen-peer-urls=http://192.168.31.90:2280 --name=yunhorn --snapshot-count=10000