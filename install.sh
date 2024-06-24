#!/bin/bash

if [ "$EUID" -ne 0 ]
  then echo "Must be run as root"
  exit
fi

mkdir /opt/cyberpower-ups-api
cp app.py /opt/cyberpower-ups-api/app.py

cp cyberpower-ups-api.service /etc/systemd/system/cyberpower-ups-api.service
systemctl daemon-reload
systemctl enable cyberpower-ups-api.service
systemctl start cyberpower-ups-api.service
