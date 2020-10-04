package io.github.vnicius.appempresas.data.repository.enterprise

import io.github.vnicius.appempresas.data.model.Enterprise


interface EnterpriseRepository {

    /**
     * Search the enterprises by a query
     *
     * @param query the term of the search
     *
     * @return array of [Enterprise]
     */
    suspend fun search(query: String): List<Enterprise>
}