package io.github.vnicius.appempresas.data.repository.enterprise

import io.github.vnicius.appempresas.data.model.Enterprise


class EnterprisesRepository(private val enterpriseProvider: RemoteEnterpriseProvider) : EnterpriseRepository {

    override suspend fun search(query: String): List<Enterprise> = enterpriseProvider.search(query)
}