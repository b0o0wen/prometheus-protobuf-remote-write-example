# prometheus-protobuf-remote-write-example

写数据到 Prometheus 官方的姿势是： 1.暴露接口由Prom 拉数据 2.推数据到Prom 的pushGateway，由Prom 到pushGateway拉

该example 骚操作的点在于：复用 Prom remote write 接口，以达到推数据的目的
（remote write 是Prom 内部传数据到remote end的接口，Prom本身不存数据）
