SUMMARY = "Plymouth Balena theme."
DESCRIPTION = "A simple plymouth theme for BalenaOS devices"

SECTION = "base"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "plymouth"

SRC_URI = " \
    file://resin.script \
    file://resin.plymouth \
    file://plymouthd.defaults \
    "

inherit allarch

do_install () {
    mkdir -p ${D}${datadir}/plymouth/themes/resin
    install -m 644 ${WORKDIR}/resin.script ${D}${datadir}/plymouth/themes/resin/
    install -m 644 ${WORKDIR}/resin.plymouth ${D}${datadir}/plymouth/themes/resin/
    install -m 644 ${WORKDIR}/plymouthd.defaults ${D}${datadir}/plymouth/
}

FILES_${PN} = "${datadir}/plymouth/*"
