package cj.netos.absorb.robot.service;

import cj.netos.absorb.robot.IAbsorberTemplateService;
import cj.netos.absorb.robot.bo.AbsorberTemplate;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjMethod;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CjService(name = "absorberTemplateService")
public class AbsorberTemplateService implements IAbsorberTemplateService {
    @CjServiceSite
    IServiceSite site;

    AbsorberTemplate absorberTemplate;

    @Override
    public AbsorberTemplate getTemplate() {
        return absorberTemplate;
    }

    @Override
    public void refresh() throws CircuitException {
        absorberTemplate = _load();
    }

    private AbsorberTemplate _load() throws CircuitException {
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(60, TimeUnit.SECONDS).build();

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String portsurl = site.getProperty("rhub.ports.document.absorber.template");

        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));

        String url = String.format("%s", portsurl);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Rest-Command", "getAbsorberTemplate")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .get()
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        String yaml = new Gson().fromJson(json, String.class);
        return new Yaml().loadAs(yaml, AbsorberTemplate.class);
    }


}
