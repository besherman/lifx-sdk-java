/*
 * The MIT License
 *
 * Created by Jarrod Boyes on 24/03/14.
 * Copyright (c) 2014 LIFX Labs. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.besherman.lifx.impl.entities.internal.structle;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Bool8;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Float32;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Int16;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.Int32;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.LxProtocolTypeBase;
import static com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.LxProtocolTypeBase.PAYLOAD_OFFSET;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt16;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt32;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt64;
import com.github.besherman.lifx.impl.entities.internal.structle.StructleTypes.UInt8;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@SuppressWarnings("unused")
public class LxProtocolLight {

    public enum WaveformType // Enum Lx::Protocol::Light::Waveform
    {
        LX_PROTOCOL_LIGHT_WAVEFORM_SAW, // LX_PROTOCOL_LIGHT_WAVEFORM_SAW = 0
        LX_PROTOCOL_LIGHT_WAVEFORM_SINE, // LX_PROTOCOL_LIGHT_WAVEFORM_SINE = 1
        LX_PROTOCOL_LIGHT_WAVEFORM_HALF_SINE, // LX_PROTOCOL_LIGHT_WAVEFORM_HALF_SINE = 2
        LX_PROTOCOL_LIGHT_WAVEFORM_TRIANGLE, // LX_PROTOCOL_LIGHT_WAVEFORM_TRIANGLE = 3
        LX_PROTOCOL_LIGHT_WAVEFORM_PULSE, // LX_PROTOCOL_LIGHT_WAVEFORM_PULSE = 4
    };

    public static final HashMap<WaveformType, Integer> waveformValueMap;
    public static final HashMap<Integer, WaveformType> waveformMap;

    static {
        waveformValueMap = new HashMap<WaveformType, Integer>();
        waveformMap = new HashMap<Integer, WaveformType>();
        waveformValueMap.put(WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_SAW, 0);
        waveformMap.put(0, WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_SAW);
        waveformValueMap.put(WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_SINE, 1);
        waveformMap.put(1, WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_SINE);
        waveformValueMap.put(WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_HALF_SINE, 2);
        waveformMap.put(2, WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_HALF_SINE);
        waveformValueMap.put(WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_TRIANGLE, 3);
        waveformMap.put(3, WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_TRIANGLE);
        waveformValueMap.put(WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_PULSE, 4);
        waveformMap.put(4, WaveformType.LX_PROTOCOL_LIGHT_WAVEFORM_PULSE);
    }

    ////////////////////////////////////////////////////////////////////////////
    //  Lx::Protocol::Light::Hsbk 
    ////////////////////////////////////////////////////////////////////////////
    public static class Hsbk extends LxProtocolTypeBase { 

        // Fields: hue, saturation, brightness, kelvin;
        private UInt16 hue;			// Field: hue - Structle::Uint16 byte offset: 0
        private UInt16 saturation;			// Field: saturation - Structle::Uint16 byte offset: 2
        private UInt16 brightness;			// Field: brightness - Structle::Uint16 byte offset: 4
        private UInt16 kelvin;			// Field: kelvin - Structle::Uint16 byte offset: 6

        private static final int PAYLOAD_SIZE = 8;

        public Hsbk(byte[] bytes) {
            if(bytes.length != PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));

            }
            int initialOffset = 0;
            
            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            hue = new UInt16(member0Data);

            byte[] member1Data = new byte[2];
            member1Data[0] = bytes[initialOffset + 2];
            member1Data[1] = bytes[initialOffset + 3];

            saturation = new UInt16(member1Data);

            byte[] member2Data = new byte[2];
            member2Data[0] = bytes[initialOffset + 4];
            member2Data[1] = bytes[initialOffset + 5];

            brightness = new UInt16(member2Data);

            byte[] member3Data = new byte[2];
            member3Data[0] = bytes[initialOffset + 6];
            member3Data[1] = bytes[initialOffset + 7];

            kelvin = new UInt16(member3Data);

        }

        public Hsbk(Object padding, UInt16 hue, UInt16 saturation, UInt16 brightness, UInt16 kelvin) {
            this.hue = hue;
            this.saturation = saturation;
            this.brightness = brightness;
            this.kelvin = kelvin;
        }

        public UInt16 getHue() {
            return hue;
        }

        public UInt16 getSaturation() {
            return saturation;
        }

        public UInt16 getBrightness() {
            return brightness;
        }

        public UInt16 getKelvin() {
            return kelvin;
        }

        @Override
        public void printMessageData() {
            hue.printValue("hue");			// Field: hue - Structle::Uint16 byte offset: 8
            saturation.printValue("saturation");			// Field: saturation - Structle::Uint16 byte offset: 8
            brightness.printValue("brightness");			// Field: brightness - Structle::Uint16 byte offset: 8
            kelvin.printValue("kelvin");			// Field: kelvin - Structle::Uint16 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt16 hue, UInt16 saturation, UInt16 brightness, UInt16 kelvin) {
            byte[] memberData;		// = name.getBytes();

            memberData = hue.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = saturation.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = kelvin.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt16 hue, UInt16 saturation, UInt16 brightness, UInt16 kelvin) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, hue, saturation, brightness, kelvin);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = hue.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = saturation.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = kelvin.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::Get 
    ////////////////////////////////////////////////////////////////////////////
    public static class Get extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 0;

        public Get(byte[] bytes) {
            this(bytes, 0);
        }

        public Get(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
        }

        public Get(Object padding) {
        }

        @Override
        public void printMessageData() {
        }


        @Override
        public byte[] getBytes() {
            return new byte[0];
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //  Lx::Protocol::Light::Set 
    ////////////////////////////////////////////////////////////////////////////
    public static class Set extends LxProtocolTypeBase { 
        // Fields: stream, color, duration;

        private UInt8 stream;			// Field: stream - Structle::Uint8 byte offset: 0
        private Hsbk color;		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 1
        private UInt32 duration;			// Field: duration - Structle::Uint32 byte offset: 9

        private static final int PAYLOAD_SIZE = 13;

        public Set(byte[] bytes) {
            this(bytes, 0);
        }

        public Set(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(State.class.getName()).log(Level.SEVERE, 
                        String.format("LX_PROTOCOL_LIGHT_SET has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }            
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            stream = new UInt8(member0Data);

            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 1];
            member1Data[1] = bytes[initialOffset + 2];
            member1Data[2] = bytes[initialOffset + 3];
            member1Data[3] = bytes[initialOffset + 4];
            member1Data[4] = bytes[initialOffset + 5];
            member1Data[5] = bytes[initialOffset + 6];
            member1Data[6] = bytes[initialOffset + 7];
            member1Data[7] = bytes[initialOffset + 8];

            color = new Hsbk(member1Data);		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 1

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 9];
            member2Data[1] = bytes[initialOffset + 10];
            member2Data[2] = bytes[initialOffset + 11];
            member2Data[3] = bytes[initialOffset + 12];

            duration = new UInt32(member2Data);

        }

        public Set(UInt8 stream, Hsbk color, UInt32 duration) {
            this.stream = stream;
            this.color = color;
            this.duration = duration;
        }

        public UInt8 getStream() {
            return stream;
        }

        public Hsbk getColor() {
            return color;
        }

        public UInt32 getDuration() {
            return duration;
        }

        @Override
        public void printMessageData() {
            stream.printValue("stream");			// Field: stream - Structle::Uint8 byte offset: 13
            color.printMessageData();		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 13
            duration.printValue("duration");			// Field: duration - Structle::Uint32 byte offset: 13
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt8 stream, Hsbk color, UInt32 duration) {
            byte[] memberData;		// = name.getBytes();

            memberData = stream.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt8 stream, Hsbk color, UInt32 duration) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, stream, color, duration);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = stream.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetWaveform 
    ////////////////////////////////////////////////////////////////////////////
    public static class SetWaveform extends LxProtocolTypeBase { 
        // Fields: stream, transient, color, period, cycles, duty_cycle, waveform;

        private UInt8 stream;			// Field: stream - Structle::Uint8 byte offset: 0
        private Bool8 transienttype;			// Field: transienttype - Structle::Bool byte offset: 1
        private Hsbk color;		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 2
        private UInt32 period;			// Field: period - Structle::Uint32 byte offset: 10
        private Float32 cycles;				// Field: cycles - Structle::Float byte offset: 14
        private Int16 duty_cycle;				// Field: duty_cycle - Structle::Int16 byte offset: 18
        private UInt8 waveform;			// Field: waveform - Structle::Uint8 byte offset: 20

        private static final int PAYLOAD_SIZE = 21;

        public SetWaveform(byte[] bytes) {
            this(bytes, 0);
        }

        public SetWaveform(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            stream = new UInt8(member0Data);

            byte[] member1Data = new byte[1];
            member1Data[0] = bytes[initialOffset + 1];

            transienttype = new Bool8(member1Data);

            byte[] member2Data = new byte[8];
            member2Data[0] = bytes[initialOffset + 2];
            member2Data[1] = bytes[initialOffset + 3];
            member2Data[2] = bytes[initialOffset + 4];
            member2Data[3] = bytes[initialOffset + 5];
            member2Data[4] = bytes[initialOffset + 6];
            member2Data[5] = bytes[initialOffset + 7];
            member2Data[6] = bytes[initialOffset + 8];
            member2Data[7] = bytes[initialOffset + 9];

            color = new Hsbk(member2Data);		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 2

            byte[] member3Data = new byte[4];
            member3Data[0] = bytes[initialOffset + 10];
            member3Data[1] = bytes[initialOffset + 11];
            member3Data[2] = bytes[initialOffset + 12];
            member3Data[3] = bytes[initialOffset + 13];

            period = new UInt32(member3Data);

            byte[] member4Data = new byte[4];
            member4Data[0] = bytes[initialOffset + 14];
            member4Data[1] = bytes[initialOffset + 15];
            member4Data[2] = bytes[initialOffset + 16];
            member4Data[3] = bytes[initialOffset + 17];

            cycles = new Float32(member4Data);

            byte[] member5Data = new byte[2];
            member5Data[0] = bytes[initialOffset + 18];
            member5Data[1] = bytes[initialOffset + 19];

            duty_cycle = new Int16(member5Data);

            byte[] member6Data = new byte[1];
            member6Data[0] = bytes[initialOffset + 20];

            waveform = new UInt8(member6Data);

        }

        public SetWaveform(Object padding, UInt8 stream, Bool8 transienttype, Hsbk color, UInt32 period, Float32 cycles, Int16 duty_cycle, UInt8 waveform) {
            this.stream = stream;
            this.transienttype = transienttype;
            this.color = color;
            this.period = period;
            this.cycles = cycles;
            this.duty_cycle = duty_cycle;
            this.waveform = waveform;
        }

        public UInt8 getStream() {
            return stream;
        }

        public Bool8 getTransienttype() {
            return transienttype;
        }

        public Hsbk getColor() {
            return color;
        }

        public UInt32 getPeriod() {
            return period;
        }

        public Float32 getCycles() {
            return cycles;
        }

        public Int16 getDuty_cycle() {
            return duty_cycle;
        }

        public UInt8 getWaveform() {
            return waveform;
        }

        @Override
        public void printMessageData() {
            stream.printValue("stream");			// Field: stream - Structle::Uint8 byte offset: 21
            transienttype.printValue("transienttype");			// Field: transienttype - Structle::Bool byte offset: 21
            color.printMessageData();		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 21
            period.printValue("period");			// Field: period - Structle::Uint32 byte offset: 21
            cycles.printValue("cycles");				// Field: cycles - Structle::Float byte offset: 21
            duty_cycle.printValue("duty_cycle");				// Field: duty_cycle - Structle::Int16 byte offset: 21
            waveform.printValue("waveform");			// Field: waveform - Structle::Uint8 byte offset: 21
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt8 stream, Bool8 transienttype, Hsbk color, UInt32 period, Float32 cycles, Int16 duty_cycle, UInt8 waveform) {
            byte[] memberData;		// = name.getBytes();

            memberData = stream.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = transienttype.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = period.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = cycles.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = duty_cycle.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = waveform.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt8 stream, Bool8 transienttype, Hsbk color, UInt32 period, Float32 cycles, Int16 duty_cycle, UInt8 waveform) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, stream, transienttype, color, period, cycles, duty_cycle, waveform);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = stream.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = transienttype.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = period.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = cycles.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = duty_cycle.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = waveform.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetDimAbsolute 
    ////////////////////////////////////////////////////////////////////////////
    public static class SetDimAbsolute extends LxProtocolTypeBase { 
        // Fields: brightness, duration;

        private Int16 brightness;				// Field: brightness - Structle::Int16 byte offset: 0
        private UInt32 duration;			// Field: duration - Structle::Uint32 byte offset: 2

        private static final int PAYLOAD_SIZE = 6;

        public SetDimAbsolute(byte[] bytes) {
            this(bytes, 0);
        }

        public SetDimAbsolute(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            brightness = new Int16(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 2];
            member1Data[1] = bytes[initialOffset + 3];
            member1Data[2] = bytes[initialOffset + 4];
            member1Data[3] = bytes[initialOffset + 5];

            duration = new UInt32(member1Data);

        }

        public SetDimAbsolute(Object padding, Int16 brightness, UInt32 duration) {
            this.brightness = brightness;
            this.duration = duration;
        }

        public Int16 getBrightness() {
            return brightness;
        }

        public UInt32 getDuration() {
            return duration;
        }

        @Override
        public void printMessageData() {
            brightness.printValue("brightness");				// Field: brightness - Structle::Int16 byte offset: 6
            duration.printValue("duration");			// Field: duration - Structle::Uint32 byte offset: 6
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Int16 brightness, UInt32 duration) {
            byte[] memberData;		// = name.getBytes();

            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Int16 brightness, UInt32 duration) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, brightness, duration);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetDimRelative 
    ////////////////////////////////////////////////////////////////////////////
    public static class SetDimRelative extends LxProtocolTypeBase { 
        // Fields: brightness, duration;

        private Int32 brightness;				// Field: brightness - Structle::Int32 byte offset: 0
        private UInt32 duration;			// Field: duration - Structle::Uint32 byte offset: 4

        private static final int PAYLOAD_SIZE = 8;

        public SetDimRelative(byte[] bytes) {
            this(bytes, 0);
        }

        public SetDimRelative(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            brightness = new Int32(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 4];
            member1Data[1] = bytes[initialOffset + 5];
            member1Data[2] = bytes[initialOffset + 6];
            member1Data[3] = bytes[initialOffset + 7];

            duration = new UInt32(member1Data);

        }

        public SetDimRelative(Object padding, Int32 brightness, UInt32 duration) {
            this.brightness = brightness;
            this.duration = duration;
        }

        public Int32 getBrightness() {
            return brightness;
        }

        public UInt32 getDuration() {
            return duration;
        }

        @Override
        public void printMessageData() {
            brightness.printValue("brightness");				// Field: brightness - Structle::Int32 byte offset: 8
            duration.printValue("duration");			// Field: duration - Structle::Uint32 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Int32 brightness, UInt32 duration) {
            byte[] memberData;		// = name.getBytes();

            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Int32 brightness, UInt32 duration) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, brightness, duration);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = brightness.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = duration.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::Rgbw 
    ////////////////////////////////////////////////////////////////////////////
    public static class Rgbw extends LxProtocolTypeBase { 

        // Fields: red, green, blue, white;
        private UInt16 red;			// Field: red - Structle::Uint16 byte offset: 0
        private UInt16 green;			// Field: green - Structle::Uint16 byte offset: 2
        private UInt16 blue;			// Field: blue - Structle::Uint16 byte offset: 4
        private UInt16 white;			// Field: white - Structle::Uint16 byte offset: 6

        private static final int PAYLOAD_SIZE = 8;

        public Rgbw(byte[] bytes) {
            this(bytes, 0);
        }

        public Rgbw(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            red = new UInt16(member0Data);

            byte[] member1Data = new byte[2];
            member1Data[0] = bytes[initialOffset + 2];
            member1Data[1] = bytes[initialOffset + 3];

            green = new UInt16(member1Data);

            byte[] member2Data = new byte[2];
            member2Data[0] = bytes[initialOffset + 4];
            member2Data[1] = bytes[initialOffset + 5];

            blue = new UInt16(member2Data);

            byte[] member3Data = new byte[2];
            member3Data[0] = bytes[initialOffset + 6];
            member3Data[1] = bytes[initialOffset + 7];

            white = new UInt16(member3Data);

        }

        public Rgbw(UInt16 red, UInt16 green, UInt16 blue, UInt16 white) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.white = white;
        }

        public UInt16 getRed() {
            return red;
        }

        public UInt16 getGreen() {
            return green;
        }

        public UInt16 getBlue() {
            return blue;
        }

        public UInt16 getWhite() {
            return white;
        }

        @Override
        public void printMessageData() {
            red.printValue("red");			// Field: red - Structle::Uint16 byte offset: 8
            green.printValue("green");			// Field: green - Structle::Uint16 byte offset: 8
            blue.printValue("blue");			// Field: blue - Structle::Uint16 byte offset: 8
            white.printValue("white");			// Field: white - Structle::Uint16 byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt16 red, UInt16 green, UInt16 blue, UInt16 white) {
            byte[] memberData;		// = name.getBytes();

            memberData = red.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = green.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = blue.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = white.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt16 red, UInt16 green, UInt16 blue, UInt16 white) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, red, green, blue, white);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = red.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = green.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = blue.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = white.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetRgbw 
    ////////////////////////////////////////////////////////////////////////////
    public static class SetRgbw extends LxProtocolTypeBase { 
        // Fields: color;

        private Rgbw color;		// Field: color - Lx::Protocol::Light::Rgbw byte offset: 0

        private static final int PAYLOAD_SIZE = 8;

        public SetRgbw(byte[] bytes) {
            this(bytes, 0);
        }

        public SetRgbw(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            color = new Rgbw(member0Data);		// Field: color - Lx::Protocol::Light::Rgbw byte offset: 0

        }

        public SetRgbw(Rgbw color) {
            this.color = color;
        }

        public Rgbw getColor() {
            return color;
        }

        @Override
        public void printMessageData() {
            color.printMessageData();		// Field: color - Lx::Protocol::Light::Rgbw byte offset: 8
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Rgbw color) {
            byte[] memberData;		// = name.getBytes();

            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Rgbw color) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, color);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::State 
    ////////////////////////////////////////////////////////////////////////////
    public static class State extends LxProtocolTypeBase { 
        // Fields: color, dim, power, label, tags;

        private Hsbk color;		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 0
        private Int16 dim;				// Field: dim - Structle::Int16 byte offset: 8
        private UInt16 power;			// Field: power - Structle::Uint16 byte offset: 10
        private String label;			// Field: label - Structle::String byte offset: 12
        private UInt64 tags;			// Field: tags - Structle::Uint64 byte offset: 44

        private static final int PAYLOAD_SIZE = 52;

        public State(byte[] bytes) {
            this(bytes, 0);
        }

        public State(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(State.class.getName()).log(Level.SEVERE, 
                        String.format("LX_PROTOCOL_LIGHT_STATE has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }
            byte[] member0Data = new byte[8];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];

            color = new Hsbk(member0Data);		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 0

            byte[] member1Data = new byte[2];
            member1Data[0] = bytes[initialOffset + 8];
            member1Data[1] = bytes[initialOffset + 9];

            dim = new Int16(member1Data);
            
            if(dim.getValue() != 0) {
                Logger.getLogger(State.class.getName()).log(Level.INFO, 
                        String.format("LX_PROTOCOL_LIGHT_STATE field dim is non null: %s", StructleTypes.bytesToString(bytes)));
            }

            byte[] member2Data = new byte[2];
            member2Data[0] = bytes[initialOffset + 10];
            member2Data[1] = bytes[initialOffset + 11];

            power = new UInt16(member2Data);

            byte[] member3Data = new byte[32];
            member3Data[0] = bytes[initialOffset + 12];
            member3Data[1] = bytes[initialOffset + 13];
            member3Data[2] = bytes[initialOffset + 14];
            member3Data[3] = bytes[initialOffset + 15];
            member3Data[4] = bytes[initialOffset + 16];
            member3Data[5] = bytes[initialOffset + 17];
            member3Data[6] = bytes[initialOffset + 18];
            member3Data[7] = bytes[initialOffset + 19];
            member3Data[8] = bytes[initialOffset + 20];
            member3Data[9] = bytes[initialOffset + 21];
            member3Data[10] = bytes[initialOffset + 22];
            member3Data[11] = bytes[initialOffset + 23];
            member3Data[12] = bytes[initialOffset + 24];
            member3Data[13] = bytes[initialOffset + 25];
            member3Data[14] = bytes[initialOffset + 26];
            member3Data[15] = bytes[initialOffset + 27];
            member3Data[16] = bytes[initialOffset + 28];
            member3Data[17] = bytes[initialOffset + 29];
            member3Data[18] = bytes[initialOffset + 30];
            member3Data[19] = bytes[initialOffset + 31];
            member3Data[20] = bytes[initialOffset + 32];
            member3Data[21] = bytes[initialOffset + 33];
            member3Data[22] = bytes[initialOffset + 34];
            member3Data[23] = bytes[initialOffset + 35];
            member3Data[24] = bytes[initialOffset + 36];
            member3Data[25] = bytes[initialOffset + 37];
            member3Data[26] = bytes[initialOffset + 38];
            member3Data[27] = bytes[initialOffset + 39];
            member3Data[28] = bytes[initialOffset + 40];
            member3Data[29] = bytes[initialOffset + 41];
            member3Data[30] = bytes[initialOffset + 42];
            member3Data[31] = bytes[initialOffset + 43];

            int endOfStringIndex;
            byte[] subString;

            endOfStringIndex = member3Data.length;

            for (int i = 0; i < member3Data.length; i++) {
                if (member3Data[i] == 0x00) {
                    endOfStringIndex = i;
                    break;
                }
            }

            subString = new byte[endOfStringIndex];
            for (int i = 0; i < endOfStringIndex; i++) {
                subString[i] = member3Data[i];
            }

            label = new String(subString);

            byte[] member4Data = new byte[8];
            member4Data[0] = bytes[initialOffset + 44];
            member4Data[1] = bytes[initialOffset + 45];
            member4Data[2] = bytes[initialOffset + 46];
            member4Data[3] = bytes[initialOffset + 47];
            member4Data[4] = bytes[initialOffset + 48];
            member4Data[5] = bytes[initialOffset + 49];
            member4Data[6] = bytes[initialOffset + 50];
            member4Data[7] = bytes[initialOffset + 51];

            tags = new UInt64(member4Data);

        }

        public State(Object padding, Hsbk color, Int16 dim, UInt16 power, String label, UInt64 tags) {
            this.color = color;
            this.dim = dim;
            this.power = power;
            this.label = label;
            this.tags = tags;
        }

        /**
         * Returns the bulb's color. 
         */
        public Hsbk getColor() {
            return color;
        }

        /**
         * Not used. Always returns 0 so far.
         */
        public Int16 getDim() {
            return dim;
        }

        /**
         * Returns 0 for OFF and 1 for ON.
         */
        public UInt16 getPower() {
            return power;
        }

        /**
         * Returns the lights label. Max 32 bytes.
         */
        public String getLabel() {
            return label;
        }

        public UInt64 getTags() {
            return tags;
        }

        @Override
        public void printMessageData() {
            color.printMessageData();		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 52
            dim.printValue("dim");				// Field: dim - Structle::Int16 byte offset: 52
            power.printValue("power");			// Field: power - Structle::Uint16 byte offset: 52
            //System.out.println( label);			// Field: label - Structle::String byte offset: 52
            tags.printValue("tags");			// Field: tags - Structle::Uint64 byte offset: 52
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Hsbk color, Int16 dim, UInt16 power, String label, UInt64 tags) {
            byte[] memberData;		// = name.getBytes();

            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = dim.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = power.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch(UnsupportedEncodingException ex) {
                throw new InternalError();
            }
            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Hsbk color, Int16 dim, UInt16 power, String label, UInt64 tags) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, color, dim, power, label, tags);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = color.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = dim.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = power.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            byte[] labelchars;
            try {
                labelchars = label.getBytes("UTF-8");
            } catch(UnsupportedEncodingException ex) {
                throw new InternalError();
            }
            //byte[] labelBytes = new byte[labelchars.length];
            byte[] labelBytes = new byte[32];

            for (int i = 0; i < 32; i++) {
                labelBytes[i] = 0x00;
            }

            for (int i = 0; i < labelchars.length; i++) {
                labelBytes[i] = labelchars[i];
            }

            memberData = labelBytes;

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = tags.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::GetRailVoltage 
    ////////////////////////////////////////////////////////////////////////////
    public static class GetRailVoltage extends LxProtocolTypeBase { 

        private static final int PAYLOAD_SIZE = 0;

        public GetRailVoltage(byte[] bytes) {
            this(bytes, 0);
        }

        public GetRailVoltage(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
        }

        public GetRailVoltage(Object padding) {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;		// = name.getBytes();

        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::StateRailVoltage 
    ////////////////////////////////////////////////////////////////////////////
    public static class StateRailVoltage extends LxProtocolTypeBase { 
        // Fields: voltage;

        private UInt32 voltage;			// Field: voltage - Structle::Uint32 byte offset: 0

        private static final int PAYLOAD_SIZE = 4;

        public StateRailVoltage(byte[] bytes) {
            this(bytes, 0);
        }

        public StateRailVoltage(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            voltage = new UInt32(member0Data);

        }

        public StateRailVoltage(Object padding, UInt32 voltage) {
            this.voltage = voltage;
        }

        public UInt32 getVoltage() {
            return voltage;
        }

        @Override
        public void printMessageData() {
            voltage.printValue("voltage");			// Field: voltage - Structle::Uint32 byte offset: 4
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, UInt32 voltage) {
            byte[] memberData;		// = name.getBytes();

            memberData = voltage.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, UInt32 voltage) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, voltage);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = voltage.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::GetTemperature 
    ////////////////////////////////////////////////////////////////////////////
    public static class GetTemperature extends LxProtocolTypeBase { 

        private static final int PAYLOAD_SIZE = 0;

        public GetTemperature(byte[] bytes) {
            this(bytes, 0);
        }

        public GetTemperature(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
        }

        public GetTemperature() {
        }

        @Override
        public void printMessageData() {
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset) {
            byte[] memberData;		// = name.getBytes();
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::StateTemperature 
    ////////////////////////////////////////////////////////////////////////////
    public static class StateTemperature extends LxProtocolTypeBase { 
        // Fields: temperature;

        private Int16 temperature;				// Field: temperature - Structle::Int16 byte offset: 0

        private static final int PAYLOAD_SIZE = 2;

        public StateTemperature(byte[] bytes) {
            this(bytes, 0);
        }

        public StateTemperature(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[2];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];

            temperature = new Int16(member0Data);
        }

        public StateTemperature(Object padding, Int16 temperature) {
            this.temperature = temperature;
        }

        public Int16 getTemperature() {
            return temperature;
        }

        @Override
        public void printMessageData() {
            temperature.printValue("temperature");				// Field: temperature - Structle::Int16 byte offset: 2
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Int16 temperature) {
            byte[] memberData;		// = name.getBytes();

            memberData = temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Int16 temperature) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, temperature);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = temperature.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::Xyz 
    ////////////////////////////////////////////////////////////////////////////
    public static class Xyz extends LxProtocolTypeBase { 
        // Fields: x, y, z;

        private Float32 x;				// Field: x - Structle::Float byte offset: 0
        private Float32 y;				// Field: y - Structle::Float byte offset: 4
        private Float32 z;				// Field: z - Structle::Float byte offset: 8

        private static final int PAYLOAD_SIZE = 12;

        public Xyz(byte[] bytes) {
            this(bytes, 0);
        }

        public Xyz(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[4];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];

            x = new Float32(member0Data);

            byte[] member1Data = new byte[4];
            member1Data[0] = bytes[initialOffset + 4];
            member1Data[1] = bytes[initialOffset + 5];
            member1Data[2] = bytes[initialOffset + 6];
            member1Data[3] = bytes[initialOffset + 7];

            y = new Float32(member1Data);

            byte[] member2Data = new byte[4];
            member2Data[0] = bytes[initialOffset + 8];
            member2Data[1] = bytes[initialOffset + 9];
            member2Data[2] = bytes[initialOffset + 10];
            member2Data[3] = bytes[initialOffset + 11];

            z = new Float32(member2Data);

        }

        public Xyz(Object padding, Float32 x, Float32 y, Float32 z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Float32 getX() {
            return x;
        }

        public Float32 getY() {
            return y;
        }

        public Float32 getZ() {
            return z;
        }

        @Override
        public void printMessageData() {
            x.printValue("x");				// Field: x - Structle::Float byte offset: 12
            y.printValue("y");				// Field: y - Structle::Float byte offset: 12
            z.printValue("z");				// Field: z - Structle::Float byte offset: 12
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Float32 x, Float32 y, Float32 z) {
            byte[] memberData;		// = name.getBytes();

            memberData = x.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = y.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = z.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Float32 x, Float32 y, Float32 z) {
            int offset = PAYLOAD_OFFSET;
            loadMessageDataWithPayloadAtOffset(messageData, offset, x, y, z);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = x.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = y.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = z.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetCalibrationCoefficients 
    ////////////////////////////////////////////////////////////////////////////
    public static class SetCalibrationCoefficients extends LxProtocolTypeBase { 
        // Fields: r, g, b, w;

        private Xyz r;		// Field: r - Lx::Protocol::Light::Xyz byte offset: 0
        private Xyz g;		// Field: g - Lx::Protocol::Light::Xyz byte offset: 12
        private Xyz b;		// Field: b - Lx::Protocol::Light::Xyz byte offset: 24
        private Xyz w;		// Field: w - Lx::Protocol::Light::Xyz byte offset: 36

        private static final int PAYLOAD_SIZE = 48;

        public SetCalibrationCoefficients(byte[] bytes) {
            this(bytes, 0);
        }

        public SetCalibrationCoefficients(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                                    
            
            byte[] member0Data = new byte[12];
            member0Data[0] = bytes[initialOffset + 0];
            member0Data[1] = bytes[initialOffset + 1];
            member0Data[2] = bytes[initialOffset + 2];
            member0Data[3] = bytes[initialOffset + 3];
            member0Data[4] = bytes[initialOffset + 4];
            member0Data[5] = bytes[initialOffset + 5];
            member0Data[6] = bytes[initialOffset + 6];
            member0Data[7] = bytes[initialOffset + 7];
            member0Data[8] = bytes[initialOffset + 8];
            member0Data[9] = bytes[initialOffset + 9];
            member0Data[10] = bytes[initialOffset + 10];
            member0Data[11] = bytes[initialOffset + 11];

            r = new Xyz(member0Data);		// Field: r - Lx::Protocol::Light::Xyz byte offset: 0

            byte[] member1Data = new byte[12];
            member1Data[0] = bytes[initialOffset + 12];
            member1Data[1] = bytes[initialOffset + 13];
            member1Data[2] = bytes[initialOffset + 14];
            member1Data[3] = bytes[initialOffset + 15];
            member1Data[4] = bytes[initialOffset + 16];
            member1Data[5] = bytes[initialOffset + 17];
            member1Data[6] = bytes[initialOffset + 18];
            member1Data[7] = bytes[initialOffset + 19];
            member1Data[8] = bytes[initialOffset + 20];
            member1Data[9] = bytes[initialOffset + 21];
            member1Data[10] = bytes[initialOffset + 22];
            member1Data[11] = bytes[initialOffset + 23];

            g = new Xyz(member1Data);		// Field: g - Lx::Protocol::Light::Xyz byte offset: 12

            byte[] member2Data = new byte[12];
            member2Data[0] = bytes[initialOffset + 24];
            member2Data[1] = bytes[initialOffset + 25];
            member2Data[2] = bytes[initialOffset + 26];
            member2Data[3] = bytes[initialOffset + 27];
            member2Data[4] = bytes[initialOffset + 28];
            member2Data[5] = bytes[initialOffset + 29];
            member2Data[6] = bytes[initialOffset + 30];
            member2Data[7] = bytes[initialOffset + 31];
            member2Data[8] = bytes[initialOffset + 32];
            member2Data[9] = bytes[initialOffset + 33];
            member2Data[10] = bytes[initialOffset + 34];
            member2Data[11] = bytes[initialOffset + 35];

            b = new Xyz(member2Data);		// Field: b - Lx::Protocol::Light::Xyz byte offset: 24

            byte[] member3Data = new byte[12];
            member3Data[0] = bytes[initialOffset + 36];
            member3Data[1] = bytes[initialOffset + 37];
            member3Data[2] = bytes[initialOffset + 38];
            member3Data[3] = bytes[initialOffset + 39];
            member3Data[4] = bytes[initialOffset + 40];
            member3Data[5] = bytes[initialOffset + 41];
            member3Data[6] = bytes[initialOffset + 42];
            member3Data[7] = bytes[initialOffset + 43];
            member3Data[8] = bytes[initialOffset + 44];
            member3Data[9] = bytes[initialOffset + 45];
            member3Data[10] = bytes[initialOffset + 46];
            member3Data[11] = bytes[initialOffset + 47];

            w = new Xyz(member3Data);		// Field: w - Lx::Protocol::Light::Xyz byte offset: 36

        }

        public SetCalibrationCoefficients(Object padding, Xyz r, Xyz g, Xyz b, Xyz w) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.w = w;
        }

        public Xyz getR() {
            return r;
        }

        public Xyz getG() {
            return g;
        }

        public Xyz getB() {
            return b;
        }

        public Xyz getW() {
            return w;
        }

        @Override
        public void printMessageData() {
            r.printMessageData();		// Field: r - Lx::Protocol::Light::Xyz byte offset: 48
            g.printMessageData();		// Field: g - Lx::Protocol::Light::Xyz byte offset: 48
            b.printMessageData();		// Field: b - Lx::Protocol::Light::Xyz byte offset: 48
            w.printMessageData();		// Field: w - Lx::Protocol::Light::Xyz byte offset: 48
        }

        public static void loadMessageDataWithPayloadAtOffset(byte[] messageData, int offset, Xyz r, Xyz g, Xyz b, Xyz w) {
            byte[] memberData;		// = name.getBytes();

            memberData = r.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = g.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = b.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            memberData = w.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                messageData[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
        }

        public static void loadMessageDataWithPayloadAtDefaultOffset(byte[] messageData, Xyz r, Xyz g, Xyz b, Xyz w) {
            int offset = PAYLOAD_OFFSET;

            loadMessageDataWithPayloadAtOffset(messageData, offset, r, g, b, w);
        }

        @Override
        public byte[] getBytes() {
            int offset = 0;

            byte[] bytes = new byte[getPayloadSize()];

            byte[] memberData;

            // = name.getBytes();        		
            memberData = r.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = g.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = b.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;
            // = name.getBytes();        		
            memberData = w.getBytes();

            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }

            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetSimpleEvent
    ////////////////////////////////////////////////////////////////////////////
    public static class SetSimpleEvent extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 36;
        
        private UInt8 index;
        private UInt64 time;
        private UInt16 power;
        private UInt32 duration;
        private Waveform waveform;
        
        public SetSimpleEvent(byte[] bytes) {
            this(bytes, 0);
        }

        public SetSimpleEvent(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];
            index = new UInt8(member0Data);
            
            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 1];
            member1Data[1] = bytes[initialOffset + 2];
            member1Data[2] = bytes[initialOffset + 3];
            member1Data[3] = bytes[initialOffset + 4];
            member1Data[4] = bytes[initialOffset + 5];
            member1Data[5] = bytes[initialOffset + 6];
            member1Data[6] = bytes[initialOffset + 7];
            member1Data[7] = bytes[initialOffset + 8];
            time = new UInt64(member1Data);            
            
            byte[] member2Data = new byte[2];
            member2Data[0] = bytes[initialOffset + 9];
            member2Data[1] = bytes[initialOffset + 10];
            power = new UInt16(member2Data);
            
            byte[] member3Data = new byte[4];
            member3Data[0] = bytes[initialOffset + 11];
            member3Data[1] = bytes[initialOffset + 12];
            member3Data[2] = bytes[initialOffset + 13];
            member3Data[3] = bytes[initialOffset + 14];
            duration = new UInt32(member3Data);
            
            waveform = new Waveform(bytes, initialOffset + 15);            
        }

        public SetSimpleEvent() {
        }
        
        
        public SetSimpleEvent(UInt8 index, UInt64 time, UInt16 power, UInt32 duration, Waveform waveform) {
            this.index = index;
            this.time = time;
            this.power = power;
            this.duration = duration;
            this.waveform = waveform;
        }
        
        public UInt8 getIndex() {
            return index;
        }

        public UInt64 getTime() {
            return time;
        }

        public UInt16 getPower() {
            return power;
        }

        public UInt32 getDuration() {
            return duration;
        }

        public Waveform getWaveform() {
            return waveform;
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            try(ByteArrayOutputStream out = new ByteArrayOutputStream(getPayloadSize())) {           
                out.write(index.getBytes());
                out.write(time.getBytes());
                out.write(power.getBytes());
                out.write(duration.getBytes());
                out.write(waveform.getBytes());
                return out.toByteArray();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::GetSimpleEvent
    ////////////////////////////////////////////////////////////////////////////
    public static class GetSimpleEvent extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 1;
        
        private UInt8 index;

        public GetSimpleEvent(byte[] bytes) {
            this(bytes, 0);
        }

        public GetSimpleEvent(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            index = new UInt8(bytes[initialOffset]);
        }

        public GetSimpleEvent(UInt8 index) {
            this.index = index;
        }
        
        public UInt8 getIndex() {
            return index;
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            return index.getBytes();
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::StateSimpleEvent
    ////////////////////////////////////////////////////////////////////////////
    public static class StateSimpleEvent extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 38;
        
        private UInt8 index;
        private UInt64 time;
        private UInt16 power;
        private UInt32 duration;
        private Waveform waveform;
        private UInt16 max;
        
        public StateSimpleEvent(byte[] bytes) {
            this(bytes, 0);
        }

        public StateSimpleEvent(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];
            index = new UInt8(member0Data);
            
            byte[] member1Data = new byte[8];
            member1Data[0] = bytes[initialOffset + 1];
            member1Data[1] = bytes[initialOffset + 2];
            member1Data[2] = bytes[initialOffset + 3];
            member1Data[3] = bytes[initialOffset + 4];
            member1Data[4] = bytes[initialOffset + 5];
            member1Data[5] = bytes[initialOffset + 6];
            member1Data[6] = bytes[initialOffset + 7];
            member1Data[7] = bytes[initialOffset + 8];
            time = new UInt64(member1Data);            
            
            byte[] member2Data = new byte[2];
            member2Data[0] = bytes[initialOffset + 9];
            member2Data[1] = bytes[initialOffset + 10];
            power = new UInt16(member2Data);
            
            byte[] member3Data = new byte[4];
            member3Data[0] = bytes[initialOffset + 11];
            member3Data[1] = bytes[initialOffset + 12];
            member3Data[2] = bytes[initialOffset + 13];
            member3Data[3] = bytes[initialOffset + 14];
            duration = new UInt32(member3Data);
            
            waveform = new Waveform(bytes, initialOffset + 15);            
            
            byte[] member4Data = new byte[2];
            member4Data[0] = bytes[initialOffset + 36];
            member4Data[1] = bytes[initialOffset + 37];
            max = new UInt16(member4Data);            
        }

        public StateSimpleEvent() {
        }
        
        public UInt8 getIndex() {
            return index;
        }

        public UInt64 getTime() {
            return time;
        }

        public UInt16 getPower() {
            return power;
        }

        public UInt32 getDuration() {
            return duration;
        }

        public Waveform getWaveform() {
            return waveform;
        }
        
        public UInt16 getMax() {
            return max;
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            try(ByteArrayOutputStream out = new ByteArrayOutputStream(getPayloadSize())) {           
                out.write(index.getBytes());
                out.write(time.getBytes());
                out.write(power.getBytes());
                out.write(duration.getBytes());
                out.write(waveform.getBytes());
                out.write(max.getBytes());
                return out.toByteArray();
            } catch(IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::GetPower
    ////////////////////////////////////////////////////////////////////////////
    public static class GetPower extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 36;
        
        private byte[] value;

        public GetPower(byte[] bytes) {
            this(bytes, 0);
        }

        public GetPower(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            value = new byte[PAYLOAD_SIZE];
            
            for(int i = initialOffset; i < initialOffset + PAYLOAD_SIZE; i++) {
                value[i - initialOffset] = bytes[i];
            }
        }

        public GetPower() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            //byte[] bytes = new byte[getPayloadSize()];
            
            return Arrays.copyOfRange(value, 0, value.length);
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetPower
    ////////////////////////////////////////////////////////////////////////////
    public static class SetPower extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 36;
        
        private byte[] value;

        public SetPower(byte[] bytes) {
            this(bytes, 0);
        }

        public SetPower(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            value = new byte[PAYLOAD_SIZE];
            
            for(int i = initialOffset; i < initialOffset + PAYLOAD_SIZE; i++) {
                value[i - initialOffset] = bytes[i];
            }
        }

        public SetPower() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            //byte[] bytes = new byte[getPayloadSize()];
            
            return Arrays.copyOfRange(value, 0, value.length);
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::StatePower
    ////////////////////////////////////////////////////////////////////////////
    public static class StatePower extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 36;
        
        private byte[] value;

        public StatePower(byte[] bytes) {
            this(bytes, 0);
        }

        public StatePower(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            value = new byte[PAYLOAD_SIZE];
            
            for(int i = initialOffset; i < initialOffset + PAYLOAD_SIZE; i++) {
                value[i - initialOffset] = bytes[i];
            }
        }

        public StatePower() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            //byte[] bytes = new byte[getPayloadSize()];
            
            return Arrays.copyOfRange(value, 0, value.length);
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }    
    

    ////////////////////////////////////////////////////////////////////////////
    // Lx::Protocol::Light::SetWaveformOptional
    ////////////////////////////////////////////////////////////////////////////
    public static class SetWaveformOptional extends LxProtocolTypeBase { 
        private static final int PAYLOAD_SIZE = 36;
        
        private byte[] value;

        public SetWaveformOptional(byte[] bytes) {
            this(bytes, 0);
        }

        public SetWaveformOptional(byte[] bytes, int initialOffset) {
            if(bytes.length != 36 + PAYLOAD_SIZE) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        String.format("payload has more data than advertised: %s", StructleTypes.bytesToString(bytes)));
            }                
            
            value = new byte[PAYLOAD_SIZE];
            
            for(int i = initialOffset; i < initialOffset + PAYLOAD_SIZE; i++) {
                value[i - initialOffset] = bytes[i];
            }
        }

        public SetWaveformOptional() {
        }

        @Override
        public void printMessageData() {
        }

        @Override
        public byte[] getBytes() {
            //byte[] bytes = new byte[getPayloadSize()];
            
            return Arrays.copyOfRange(value, 0, value.length);
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }    
    
    public static class Waveform { 
        // Fields: stream, transient, color, period, cycles, duty_cycle, waveform;

        private UInt8 stream;			// Field: stream - Structle::Uint8 byte offset: 0
        private Bool8 transienttype;			// Field: transienttype - Structle::Bool byte offset: 1
        private Hsbk color;		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 2
        private UInt32 period;			// Field: period - Structle::Uint32 byte offset: 10
        private Float32 cycles;				// Field: cycles - Structle::Float byte offset: 14
        private Int16 skew_ratio;				// Field: duty_cycle - Structle::Int16 byte offset: 18
        private UInt8 waveform;			// Field: waveform - Structle::Uint8 byte offset: 20

        private static final int PAYLOAD_SIZE = 21;

        public Waveform(byte[] bytes) {
            this(bytes, 0);
        }

        public Waveform(byte[] bytes, int initialOffset) {
            byte[] member0Data = new byte[1];
            member0Data[0] = bytes[initialOffset + 0];

            stream = new UInt8(member0Data);

            byte[] member1Data = new byte[1];
            member1Data[0] = bytes[initialOffset + 1];

            transienttype = new Bool8(member1Data);

            byte[] member2Data = new byte[8];
            member2Data[0] = bytes[initialOffset + 2];
            member2Data[1] = bytes[initialOffset + 3];
            member2Data[2] = bytes[initialOffset + 4];
            member2Data[3] = bytes[initialOffset + 5];
            member2Data[4] = bytes[initialOffset + 6];
            member2Data[5] = bytes[initialOffset + 7];
            member2Data[6] = bytes[initialOffset + 8];
            member2Data[7] = bytes[initialOffset + 9];

            color = new Hsbk(member2Data);		// Field: color - Lx::Protocol::Light::Hsbk byte offset: 2

            byte[] member3Data = new byte[4];
            member3Data[0] = bytes[initialOffset + 10];
            member3Data[1] = bytes[initialOffset + 11];
            member3Data[2] = bytes[initialOffset + 12];
            member3Data[3] = bytes[initialOffset + 13];

            period = new UInt32(member3Data);

            byte[] member4Data = new byte[4];
            member4Data[0] = bytes[initialOffset + 14];
            member4Data[1] = bytes[initialOffset + 15];
            member4Data[2] = bytes[initialOffset + 16];
            member4Data[3] = bytes[initialOffset + 17];

            cycles = new Float32(member4Data);

            byte[] member5Data = new byte[2];
            member5Data[0] = bytes[initialOffset + 18];
            member5Data[1] = bytes[initialOffset + 19];

            skew_ratio = new Int16(member5Data);

            byte[] member6Data = new byte[1];
            member6Data[0] = bytes[initialOffset + 20];

            waveform = new UInt8(member6Data);

        }

        public Waveform(UInt8 stream, Bool8 transienttype, Hsbk color, UInt32 period, Float32 cycles, Int16 skew_ratio, UInt8 waveform) {
            this.stream = stream;
            this.transienttype = transienttype;
            this.color = color;
            this.period = period;
            this.cycles = cycles;
            this.skew_ratio = skew_ratio;
            this.waveform = waveform;
        }

        public UInt8 getStream() {
            return stream;
        }

        public Bool8 getTransienttype() {
            return transienttype;
        }

        public Hsbk getColor() {
            return color;
        }

        public UInt32 getPeriod() {
            return period;
        }

        public Float32 getCycles() {
            return cycles;
        }

        public Int16 getSkewRatio() {
            return skew_ratio;
        }

        public UInt8 getWaveform() {
            return waveform;
        }
        
        public byte[] getBytes() {
            int offset = 0;
            byte[] bytes = new byte[getPayloadSize()];
            byte[] memberData; 
            
            memberData = stream.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = transienttype.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = color.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = period.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = cycles.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = skew_ratio.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;
            
            memberData = waveform.getBytes();
            for (int i = 0; i < (memberData.length); i++) {
                bytes[(offset + i)] = memberData[i];
            }
            offset += memberData.length;

            return bytes;
        }

        public static int getPayloadSize() {
            return PAYLOAD_SIZE;
        }
    }

    
    
}
