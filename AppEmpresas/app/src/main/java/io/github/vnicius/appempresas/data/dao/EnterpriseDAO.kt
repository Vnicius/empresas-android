package io.github.vnicius.appempresas.data.dao

import io.github.vnicius.appempresas.data.model.Enterprise

interface EnterpriseDAO {

    /**
     * Search the enterprises by a query
     *
     * @param query the term of the search
     *
     * @return array of [Enterprise]
     */
    suspend fun search(query: String): List<Enterprise>

    /**
     * Get the [Enterprise] by the id
     *
     * @param id of the enterprise
     *
     * @return the [Enterprise] for that [id] value
     */
    suspend fun getEnterpriseById(id: Int): Enterprise?
}