# General
Simple API for retrieving information about the CyberPower UPS

# Prerequisites
Allow executing pwrstat with sudo NOPASSWD right.

/etc/sudoers.d/pwrstat:
`%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat`

# Usage
Example docker compose:
```
version: '3'
services:
  cyberpower-ups-api:
    ports:
      - "9090:8080"
    container_name: cyberpower-ups-api
    image: ghcr.io/antonvovk/cyberpower-ups-api:1.0.1
    restart: unless-stopped
    volumes:
      - /etc/localtime:/etc/localtime:ro
      - /usr/sbin/pwrstat:/usr/sbin/pwrstat:ro
      - /var/pwrstatd.ipc:/var/pwrstatd.ipc:ro
      - /var/pwrstatd.dev:/var/pwrstatd.dev:ro
```
