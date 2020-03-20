FROM grafana/grafana:6.7.0
ADD ./provisioning /etc/grafana/provisioning
ADD ./config.ini /etc/grafana/config.ini
ADD ./redis-dashboard/redis-dashboard.json /var/lib/grafana/dashboards/redis-dashboard.json