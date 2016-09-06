package uk.gov.ons.businessregister.utils

import com.google.common.base.CharMatcher

class StringUtils {

    static def String removeWhitespaces(String value) {
        CharMatcher.WHITESPACE.removeFrom(value)
    }

}
