package br.com.wpattern.multi.tenancy.utils

import br.com.zup.spring.tenant.TenantContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TenantHandle: GenericFilterBean() {

    companion object {
        const val TENANT_HEADER = "Tenant-Header"
        const val DEFAULT_TENANT = "public"
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        if (request !is HttpServletRequest || response !is HttpServletResponse) {
            throw IllegalArgumentException("Unhandled type.")
        }

        val tenant = request.getHeader(TENANT_HEADER) ?: DEFAULT_TENANT
        TenantContextHolder.set(tenant)

        chain?.doFilter(request, response)
    }

}