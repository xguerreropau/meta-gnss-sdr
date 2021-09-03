SUMMARY = "Library for interfacing with IIO devices"
HOMEPAGE = "https://wiki.analog.com/resources/tools-software/linux-software/libiio"
SECTION = "libs"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=7c13b3376cea0ce68d2d2da0a1b3a72c"
PR = "r0"

SRCREV = "8d6ad2926e84c39868907fdd846e55a0c7e8f5a4"
GIT_BRANCH = "2019_R1"

SRC_URI = "git://github.com/analogdevicesinc/libiio.git;branch=${GIT_BRANCH};protocol=https "

S = "${WORKDIR}/git"

inherit cmake python3native systemd

DEPENDS = " \
    flex-native bison-native libaio \
    ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'avahi', '', d)} \
"

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -DUDEV_RULES_INSTALL_DIR=${nonarch_base_libdir}/udev/rules.d \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD=ON -DSYSTEMD_UNIT_INSTALL_DIR=${systemd_system_unitdir}', '', d)} \
"

PACKAGECONFIG ??= "usb_backend network_backend"

PACKAGECONFIG[usb_backend] = "-DWITH_USB_BACKEND=ON,-DWITH_USB_BACKEND=OFF,libusb1,libxml2"
PACKAGECONFIG[network_backend] = "-DWITH_NETWORK_BACKEND=ON,-DWITH_NETWORK_BACKEND=OFF,libxml2"
PACKAGECONFIG[libiio-python3] = "-DPYTHON_BINDINGS=ON,-DPYTHON_BINDINGS=OFF"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'libiio-python3', 'distutils3-base', '', d)}

PACKAGES =+ "${PN}-iiod ${PN}-tests ${PN}-${PYTHON_PN}"

RDEPENDS_${PN}-${PYTHON_PN} = "${PN} ${PYTHON_PN}-ctypes ${PYTHON_PN}-stringold"

FILES_${PN}-iiod = " \
    ${sbindir}/iiod \
    ${systemd_system_unitdir}/iiod.service \
"

FILES_${PN}-tests = " \
    ${bindir}/iio_attr \
    ${bindir}/iio_info \
    ${bindir}/iio_reg \
    ${bindir}/iio_readdev \
    ${bindir}/ \
"

FILES_${PN}-${PYTHON_PN} = "${PYTHON_SITEPACKAGES_DIR}"

SYSTEMD_PACKAGES = "${PN}-iiod"
SYSTEMD_SERVICE_${PN}-iiod = "iiod.service"