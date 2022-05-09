package com.deploydesexta.talents.transport.graphql

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.core.repositories.TalentRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class TalentDataFetcher(
    private val talentRepository: TalentRepository,
) {

    @DgsQuery
    suspend fun talents(@InputArgument("title") titleFilter: String? = ""): List<Talent> {
        return if (titleFilter != null) {
            talentRepository.findAllByNameContaining(filter = titleFilter)
        } else {
            talentRepository.findAll()
        }
    }
}