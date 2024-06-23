# Prerequisites
Allow executing pwrstat with sudo NOPASSWD right.

/etc/sudoers.d/pwrstat:
`%sudo ALL=(ALL) NOPASSWD: /usr/sbin/pwrstat`
