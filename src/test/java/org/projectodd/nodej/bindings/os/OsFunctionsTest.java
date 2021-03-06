package org.projectodd.nodej.bindings.os;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.net.UnknownHostException;

import org.dynjs.runtime.DynArray;
import org.dynjs.runtime.DynObject;
import org.dynjs.runtime.JSFunction;
import org.dynjs.runtime.Types;
import org.junit.Test;
import org.projectodd.nodej.NodejTestSupport;

public class OsFunctionsTest extends NodejTestSupport {
    @Test
    public void testOsHostname() throws UnknownHostException {
        assertThat(eval("process.binding('os').getHostname")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getHostname()")).isEqualTo(java.net.InetAddress.getLocalHost().getHostName());
    }
    
    @Test
    public void testOsLoadavg() {
        assertThat(eval("process.binding('os').getLoadAvg")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getLoadAvg()")).isInstanceOf(DynArray.class);
        assertThat(eval("process.binding('os').getLoadAvg().length")).isEqualTo(3L);
        assertThat(eval("process.binding('os').getLoadAvg()[0] > 0")).isEqualTo(true);
        assertThat(eval("process.binding('os').getLoadAvg()[1] > 0")).isEqualTo(true);
        assertThat(eval("process.binding('os').getLoadAvg()[2] > 0")).isEqualTo(true);
    }
    
    @Test
    public void testOSUptime() {
        assertThat(eval("process.binding('os').getUptime")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getUptime()>0")).isEqualTo(true);
    }

    @Test
    public void testOSFreeMem() {
        assertThat(eval("process.binding('os').getFreeMem")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getFreeMem()>0")).isEqualTo(true);
    }

    @Test
    public void testOSTotalMem() {
        assertThat(eval("process.binding('os').getTotalMem")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getTotalMem()>0")).isEqualTo(true);
    }
    
    @Test
    public void testOSType() {
        assertThat(eval("process.binding('os').getOSType")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getOSType()")).isNotEqualTo(Types.UNDEFINED);
    }
    
    @Test
    public void testOSRelease() {
        assertThat(eval("process.binding('os').getOSRelease")).isInstanceOf(JSFunction.class);
        assertThat(eval("process.binding('os').getOSRelease()")).isNotEqualTo(Types.UNDEFINED);
    }
    
    @Test
    public void testCpus() {
        assertThat(eval("process.binding('os').getCPUs")).isInstanceOf(JSFunction.class);
        assertThat(eval("var cpus = process.binding('os').getCPUs(); cpus")).isInstanceOf(DynArray.class);
        assertThat(eval("cpus.length > 0")).isEqualTo(true);
        assertThat(eval("cpus[0].model")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].speed")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].times")).isInstanceOf(DynObject.class);
        assertThat(eval("cpus[0].times.user")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].times.nice")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].times.sys")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].times.idle")).isNotEqualTo(Types.UNDEFINED);
        assertThat(eval("cpus[0].times.irq")).isNotEqualTo(Types.UNDEFINED);
    }
    
    @Test
    public void testInterfaceAddresses() {
        assertThat(eval("process.binding('os').getInterfaceAddresses")).isInstanceOf(JSFunction.class);
        assertThat(eval("var ifaces = process.binding('os').getInterfaceAddresses(); ifaces")).isNotEqualTo(Types.UNDEFINED);
    }
    
    @Test
    public void testRequireOs() {
        assertThat(eval("require('os')")).isInstanceOf(DynObject.class);
    }
    
    @Test
    public void testOsModuleFunctions() throws UnknownHostException {
        eval("var os = require('os')");
        // These aren't testing the values, just that the API is at least
        // returning expected types
        assertThat(new File((String) eval("os.tmpDir()")).isDirectory()).isTrue();
        assertThat(eval("os.hostname()")).isEqualTo(java.net.InetAddress.getLocalHost().getHostName());
        assertThat(eval("os.type()")).isInstanceOf(String.class);
        assertThat(eval("os.platform()")).isInstanceOf(String.class);
        assertThat(eval("os.arch()")).isInstanceOf(String.class);
        assertThat(eval("os.release()")).isInstanceOf(String.class);
        assertThat(eval("os.uptime()")).isInstanceOf(Number.class);
        assertThat(eval("os.loadavg()")).isInstanceOf(DynArray.class);
        assertThat(eval("os.totalmem()")).isInstanceOf(Number.class);
        assertThat(eval("os.freemem()")).isInstanceOf(Number.class);
        assertThat(eval("os.cpus()")).isInstanceOf(DynArray.class);
        assertThat(eval("os.networkInterfaces()")).isInstanceOf(DynObject.class);
        assertThat(eval("os.EOL")).isInstanceOf(String.class);
    }
}
