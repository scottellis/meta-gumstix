DESCRIPTION = "The most basic Gumstix image"

inherit image

BASE_INSTALL = " \
  ${MACHINE_EXTRA_RRECOMMENDS} \
  avahi-systemd avahi-utils \
  base-files \
  base-passwd \
  bash \
  coreutils \
  dbus \
  devmem2 \
  memtester \
  netbase \
  ntp-systemd \
  rsyslog-systemd \
  tinylogin
  systemd systemd-compat-units \
  u-boot-mkimage \
  u-boot-sakoman-fw-utils \
  udev \
  udisks udisks-systemd \
  upower \
  util-linux \
  which \
"

IMAGE_INSTALL += " \
  bash \
  task-proper-tools \
  task-audio \
  kmod \
  task-firmware \
  ${BASE_INSTALL} \
 "
# this section removes remnants of legacy sysvinit support
# for packages installed above
IMAGE_FILE_BLACKLIST += " \
                        /etc/init.d/NetworkManager \
                        /etc/init.d/avahi-daemon \
                        /etc/init.d/dbus-1 \
                        /etc/init.d/dnsmasq \
                        /etc/init.d/networking \
                        /etc/init.d/ntpd \
                        /etc/init.d/sshd \
                        /etc/init.d/udev \
                        /etc/init.d/udev-cache \
                       "

remove_blacklist_files() {
	for i in ${IMAGE_FILE_BLACKLIST}; do
		rm -rf ${IMAGE_ROOTFS}$i
	done

}

ROOTFS_POSTPROCESS_COMMAND =+ "remove_blacklist_files ; "

