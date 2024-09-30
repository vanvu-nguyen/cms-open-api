package Commons;

import ModuleList.LoginModule;
import ModuleList.PayerModule;

public class ModuleGenerator {
    public static LoginModule getLoginModule() {
        return new LoginModule();
    }
    public static PayerModule getPayerModule() {
        return new PayerModule();
    }
}
