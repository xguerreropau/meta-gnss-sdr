SUMMARY = "GNSS-SDR: An open source software defined GNSS receiver"
AUTHOR = "Carles Fernandez-Prades <carles.fernandez@cttc.es>"
HOMEPAGE = "https://gnss-sdr.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=31f43bdb1ab7b19dae6e891241ca0568"

DEPENDS = "volk gnuradio armadillo gflags glog matio \
           gnutls python3-mako python3-mako-native python3-six python3-six-native"

PACKAGECONFIG ??= "osmosdr unittest"

PACKAGECONFIG[osmosdr] = "-DENABLE_OSMOSDR=ON,-DENABLE_OSMOSDR=OFF,rtl-sdr libbladerf gr-osmosdr, "
PACKAGECONFIG[unittest] = "-DENABLE_UNIT_TESTING=ON,-DENABLE_UNIT_TESTING=OFF, gtest, "
PACKAGECONFIG[logging] = "-DENABLE_LOG=ON,-DENABLE_LOG=OFF "

export BUILD_SYS
export HOST_SYS="${MULTIMACH_TARGET_SYS}"

EXTRA_OECMAKE += " -DPYTHON_EXECUTABLE=/usr/bin/python3 "
PV = "0.0.9"

SRCREV = "d93e60caafb0520f47e53a001bc075a816ddf028"

# Make it easy to test against branches
GIT_BRANCH = "next"

SRC_URI = "git://github.com/gnss-sdr/gnss-sdr.git;branch=${GIT_BRANCH};protocol=https "

S="${WORKDIR}/git"

inherit distutils-base cmake pkgconfig

PACKAGES = "gnss-sdr-dbg gnss-sdr"

FILES_${PN} = "${bindir}/gnss-sdr \
     ${bindir}/volk_gnsssdr_profile \
     ${bindir}/volk_gnsssdr-config-info \
     ${bindir}/front-end-cal \
     /usr/share/man/man1/volk_gnsssdr-config-info.1.gz \
     /usr/share/man/man1/gnss-sdr.1.gz \
     /usr/share/man/man1/front-end-cal.1.gz \
     /usr/share/man/man1/volk_gnsssdr_profile.1.gz \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_GN3S_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E1_USRP_X300_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L2C_USRP_X300_realtime.conf \
     /usr/share/gnss-sdr/conf/default.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E5a_IFEN_CTTC.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_rtl_tcp_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_nsr_twobit_packed.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multisource_Hybrid_ishort.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_gr_complex.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L2_M_Flexiband_bin_file_III_1b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_USRP_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_Flexiband_realtime_III_1a.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_SPIR.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_Flexiband_realtime_II_3b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Hybrid_byte_sim.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multisource_Hybrid_nsr.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E5a.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_nsr.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_gr_complex_gpu.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_USRP_X300_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E1_acq_QuickSync.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_ishort.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Hybrid_ishort.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Hybrid_byte.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_L2_Galileo_E1B_Flexiband_bin_file_III_1b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_L2_Flexiband_realtime_III_1b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L2C_USRP1_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_acq_QuickSync.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_rtlsdr_realtime.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_Flexiband_bin_file_III_1a.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_USRP_X300_realtime.conf \
     /usr/share/gnss-sdr/conf/front-end-cal.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Hybrid_gr_complex.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_Flexiband_realtime_I_1b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_multichannel_GPS_L1_Flexiband_realtime_III_1b.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_GPS_L1_two_bits_cpx.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Hybrid_nsr.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E1_ishort.conf \
     /usr/share/gnss-sdr/conf/gnss-sdr_Galileo_E1_nsr.conf \
"

FILES_${PN}-dbg += " \
     /usr/src/debug/gnss-sdr/* \
     ${bindir}/.debug/volk_gnsssdr-config-info \
     ${bindir}/.debug/gnss-sdr \
     ${bindir}/.debug/volk_gnsssdr_profile \
     ${bindir}/.debug/front-end-cal \
"
