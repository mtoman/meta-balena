SUMMARY = "Plymouth Balena theme."
DESCRIPTION = "A simple plymouth theme for BalenaOS devices"

SECTION = "base"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "jq-native"
RDEPENDS_${PN} = "liberation-fonts plymouth"

SRC_URI = " \
    file://resin-flasher.script \
    file://resin-flasher.plymouth \
    file://plymouthd.defaults \
    "

inherit allarch

do_install () {
    mkdir -p ${D}${datadir}/plymouth/themes/resin-flasher
    install -m 644 ${WORKDIR}/resin-flasher.script ${D}${datadir}/plymouth/themes/resin-flasher/
    instructions=$(jq -r ".stateInstructions.postProvisioning" ${RESIN_COREBASE}/../../../${MACHINE}.json | tr -d "[]\"\\n" | tr -s " " | tr "," "@")
    sed -i "s#%%INSTRUCTIONS%%#${instructions}#" ${D}${datadir}/plymouth/themes/resin-flasher/resin-flasher.script
    sed -i 's#@#\\n#g' ${D}${datadir}/plymouth/themes/resin-flasher/resin-flasher.script
    install -m 644 ${WORKDIR}/resin-flasher.plymouth ${D}${datadir}/plymouth/themes/resin-flasher/
    install -m 644 ${WORKDIR}/plymouthd.defaults ${D}${datadir}/plymouth/
}

FILES_${PN} = "${datadir}/plymouth/*"
