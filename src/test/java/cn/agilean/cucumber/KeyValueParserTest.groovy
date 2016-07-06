package cn.agilean.cucumber;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test

import spock.lang.Specification
import spock.lang.Unroll;;

public class KeyValueParserTest extends Specification {

    KeyValueParser bean = new KeyValueParser();

    @Unroll
    def "use split #name"() {
        when:
        Map<String, String> result = bean.parse(text);

        then:
        result == expect;
        where:

        name << ['等号', '空格', '冒号']
        text <<[
'''
a=b
b=c
''',
'''
a b
b c
''',
'''
a : b
b : c
'''
        ]
        expect = ["a":"b","b":"c"]
    }
}
