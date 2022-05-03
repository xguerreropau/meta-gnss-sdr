SUMMARY = "Recipe for building an external ezdma Linux kernel module"
SECTION = "PETALINUX/modules"
HOMEPAGE = "https://github.com/jeremytrimble/ezdma"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"
PR = "r1"

inherit module

SRC_URI = "file://Makefile \
    file://ezdma.c \
    file://COPYING \
"

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

KERNEL_MODULE_AUTOLOAD += "ezdma"
