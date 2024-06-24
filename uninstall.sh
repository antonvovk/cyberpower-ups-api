#!/bin/bash

if [ "$EUID" -ne 0 ]
  then echo "Must be run as root"
  exit
fi

rm -rf /opt/cyberpower-ups-api

systemctl disable cyberpower-ups-api.service
systemctl stop cyberpower-ups-api.service
rm /etc/systemd/system/cyberpower-ups-api.service
systemctl daemon-reload
