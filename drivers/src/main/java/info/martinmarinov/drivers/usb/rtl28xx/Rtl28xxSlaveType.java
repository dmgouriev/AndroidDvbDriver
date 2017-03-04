/*
 * This is an Android user space port of DVB-T Linux kernel modules.
 *
 * Copyright (C) 2017 Martin Marinov <martintzvetomirov at gmail com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package info.martinmarinov.drivers.usb.rtl28xx;

import android.content.res.Resources;

import info.martinmarinov.drivers.usb.DvbFrontend;

enum Rtl28xxSlaveType {
    SLAVE_DEMOD_NONE(new FrontendCreator() {
        @Override
        public DvbFrontend createFrontend(Rtl28xxTunerType tuner, Rtl28xxDvbDevice.Rtl28xxI2cAdapter i2CAdapter, Resources resources) {
            return new Rtl2832Frontend(0x10, 28_800_000L, tuner, i2CAdapter, resources);
        }
    }),
    SLAVE_DEMOD_MN88472(new FrontendCreator() {
        @Override
        public DvbFrontend createFrontend(Rtl28xxTunerType tuner, Rtl28xxDvbDevice.Rtl28xxI2cAdapter i2CAdapter, Resources resources) {
            // No DVB-T2 implementation yet, fall back to DVB-T only
            return new Rtl2832Frontend(0x10, 28_800_000L, tuner, i2CAdapter, resources);
        }
    }),
    SLAVE_DEMOD_MN88473(new FrontendCreator() {
        @Override
        public DvbFrontend createFrontend(Rtl28xxTunerType tuner, Rtl28xxDvbDevice.Rtl28xxI2cAdapter i2CAdapter, Resources resources) {
            // No DVB-T2 implementation yet, fall back to DVB-T only
            return new Rtl2832Frontend(0x10, 28_800_000L, tuner, i2CAdapter, resources);
        }
    });

    private final FrontendCreator frontendCreator;

    Rtl28xxSlaveType(FrontendCreator frontendCreator) {
        this.frontendCreator = frontendCreator;
    }

    DvbFrontend createFrontend(Rtl28xxTunerType tunerType, Rtl28xxDvbDevice.Rtl28xxI2cAdapter i2cAdapter, Resources resources) {
        return frontendCreator.createFrontend(tunerType, i2cAdapter, resources);
    }

    private interface FrontendCreator {
        DvbFrontend createFrontend(Rtl28xxTunerType tunerType, Rtl28xxDvbDevice.Rtl28xxI2cAdapter i2cAdapter, Resources resources);
    }
}
