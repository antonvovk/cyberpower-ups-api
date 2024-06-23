# General
Simple API for retrieving information about the CyberPower UPS

# Prerequisites
 - Allow executing pwrstat with sudo NOPASSWD right
 - Create executable which will run pwrstat with sudo

```
echo "%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat" | sudo tee /etc/sudoers.d/pwrstat
echo -e '#!/bin/bash\n\nsudo pwrstat "$@"' | sudo tee /usr/sbin/pwrstat-as-sudo
sudo chmod +x /usr/sbin/pwrstat-as-sudo
```


# Usage
Example docker compose:
```
version: '3'
services:
  cyberpower-ups-api:
    ports:
      - "9090:8080"
    container_name: cyberpower-ups-api
    image: ghcr.io/antonvovk/cyberpower-ups-api:1.0.6
    restart: unless-stopped
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - /usr/sbin/pwrstat-as-sudo:/usr/sbin/pwrstat:ro
      - /var/pwrstatd.ipc:/var/pwrstatd.ipc:ro
      - /var/pwrstatd.dev:/var/pwrstatd.dev:ro
    privileged: true
```
