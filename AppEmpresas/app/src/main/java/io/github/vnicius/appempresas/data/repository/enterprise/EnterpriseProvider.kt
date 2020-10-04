package io.github.vnicius.appempresas.data.repository.enterprise

import io.github.vnicius.appempresas.data.dao.EnterpriseDAO
import io.github.vnicius.appempresas.data.model.Enterprise


class EnterpriseProvider(private val enterpriseDAO: EnterpriseDAO) : RemoteEnterpriseProvider {

    override suspend fun search(query: String): List<Enterprise> = enterpriseDAO.search(query)
}