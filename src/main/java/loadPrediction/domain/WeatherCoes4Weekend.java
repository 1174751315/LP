/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.domain;

import  loadPrediction.domain.visitors.IDomainVisitor;

/**
 * 李倍存 创建于 2015-03-02 19:08。电邮 1174751315@qq.com。
 */
public class WeatherCoes4Weekend extends WeatherCoes4Workday implements IDomain {
    public WeatherCoes4Weekend() {
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
