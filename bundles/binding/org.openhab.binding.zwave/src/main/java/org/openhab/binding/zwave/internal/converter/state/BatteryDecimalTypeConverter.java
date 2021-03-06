/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.zwave.internal.converter.state;

import org.openhab.core.library.types.DecimalType;

/**
 * Converts from a {@link Integer} to a {@link DecimalType}
 * Only processes for the BATTERY command class.
 *
 * @author Jan-Willem Spuij
 * @since 1.4.0
 */
public class BatteryDecimalTypeConverter extends ZWaveStateConverter<Integer, DecimalType> {

    private static final DecimalType HUNDRED = new DecimalType(100);

    /**
     * {@inheritDoc}
     */
    @Override
    protected DecimalType convert(Integer value) {
        // 0xFF is low battery warning
        if (value <= 0 || value == 0xFF) {
            return DecimalType.ZERO;
        } else if (value > 0 && value < 100) {
            return new DecimalType(value);
        } else {
            return HUNDRED;
        }
    }
}
