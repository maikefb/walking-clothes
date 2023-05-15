package br.com.cwi.crescer.api.repository.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.domain.TagItem;
import br.com.cwi.crescer.api.dto.BuscaItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    Optional<Item> findByIdItem(Integer id);

    @Query("Select p from Item p where idItem in :ids ")
    List<Item> buscarItensPorId(@Param("ids") Iterable<Integer> ids);

    List<Item> findByIdArmarioIdArmarioAndStatus(Integer idArmario, Status status);

    List<Item> findByTagsEstilosContains(List<TagItem> tags);

    @Query("Select count(*) FROM Item i where i.idArmario.usuario.idUsuario = ?1 and i.status = 'D' ")
    Integer quantidadeItensAVendaUsuario(Integer idUsuario);

    @Query("Select count(*)FROM Item i where i.idArmario.usuario.idUsuario = ?1 and i.status= 'I' ")
    Integer quantidadeItensVendidosUsuario(Integer idUsuario);

    @Query("Select count(*) FROM Item i where i.idArmario.idArmario = ?1 and i.status= 'D'")
    Integer quantidadeItensAVendaArmario(Integer idArmario);

    @Query(value = "SELECT DISTINCT  I.ID_ITEM as idItem, I.TITULO,I.PRECO, I.TAMANHO  , M.NOME ,E.LATITUDE, E.LONGITUDE " +
            " FROM ITEM I  " +
            " LEFT JOIN TAG_ITEM TI ON  I.ID_ITEM = TI.ID_ITEM  " +
            " INNER JOIN ARMARIO A ON A.ID_ARMARIO = I.ID_ARMARIO  " +
            " LEFT JOIN TAGS T ON TI.ID_TAG =  T.ID_TAG  " +
            " INNER JOIN TAGS TA ON TA.ID_TAG = A.ID_TAG  " +
            " INNER JOIN USUARIO U ON U.ID_USUARIO = A.ID_USUARIO " +
            " INNER JOIN ENDERECO E ON E.ID_ENDERECO = U.ID_ENDERECO " +
            " INNER JOIN MUNICIPIO M ON M.ID_MUNICIPIO = E.ID_MUNICIPIO " +
            " WHERE (UPPER(I.TITULO) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%')) " +
            " OR UPPER(I.DESCRICAO) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%')) " +
            " OR UPPER(T.NOME) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%'))  " +
            " ) " +
            " AND ( ?2 is null or I.TAMANHO = ?2 ) " +
            " AND ( ?3 is null or I.TIPO = ?3 ) " +
            " AND ( ?4 is null or I.COR = ?4 ) " +
            " AND ( ?5 is null or I.ESTADO_USO = ?5 ) " +
            " AND ( ?7 is null or I.PRECO BETWEEN TO_NUMBER( ?6 ) AND TO_NUMBER( ?7 ) ) " +
            " AND ( ?8 is null or I.ACEITA_TROCA = TO_NUMBER( ?8 ) ) " +
            " AND I.STATUS = 'D' " +
            " AND 6378.137 * ACos ( Cos( ( ?9 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?10 )) ) + (Sin(  ( ?9 )) * Sin( (E.LATITUDE *(3.1415/180) ) )) ) < ?11" +
            " ORDER BY 6378.137 * ACos ( Cos( ( ?9 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?10 )) ) + (Sin(  ( ?9 )) * Sin( (E.LATITUDE *(3.1415/180) ) )) )"
            ,
            countQuery = "      SELECT COUNT (1)" +
                    "           FROM ITEM I  " +
                    "           LEFT JOIN TAG_ITEM TI ON  I.ID_ITEM = TI.ID_ITEM  " +
                    "           INNER JOIN ARMARIO A ON A.ID_ARMARIO = I.ID_ARMARIO  " +
                    "           LEFT JOIN TAGS T ON TI.ID_TAG =  T.ID_TAG  " +
                    "           INNER JOIN TAGS TA ON TA.ID_TAG = A.ID_TAG  " +
                    "           INNER JOIN USUARIO U ON U.ID_USUARIO = A.ID_USUARIO " +
                    "           INNER JOIN ENDERECO E ON E.ID_ENDERECO = U.ID_ENDERECO " +
                    "           INNER JOIN MUNICIPIO M ON M.ID_MUNICIPIO = E.ID_MUNICIPIO " +
                    "           WHERE (UPPER(I.TITULO) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%')) " +
                    "               OR UPPER(I.DESCRICAO) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%')) " +
                    "               OR UPPER(T.NOME) LIKE UPPER( CONCAT(CONCAT('%', ?1 ),'%'))  " +
                    "           ) " +
                    "           AND ( ?2 is null or I.TAMANHO = ?2 ) " +
                    "           AND ( ?3 is null or I.TIPO = ?3 ) " +
                    "           AND ( ?4 is null or I.COR = ?4 ) " +
                    "           AND ( ?5 is null or I.ESTADO_USO = ?5 ) " +
                    "           AND ( ?7 is null or I.PRECO BETWEEN TO_NUMBER( ?6 ) AND TO_NUMBER( ?7 ) ) " +
                    "           AND ( ?8 is null or I.ACEITA_TROCA = TO_NUMBER( ?8 ) ) " +
                    "           AND I.STATUS = 'D' " +
                    "           AND 6378.137 * ACos ( Cos( ( ?9 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?10 )) ) + (Sin(  ( ?9 )) * Sin( (E.LATITUDE *(3.1415/180) ) )) ) < ?11",
            nativeQuery = true)
    Page<BuscaItemDto> findByFilter(
            String nome,
            Optional<String> tamanho,
            Optional<String> tipo,
            Optional<String> cor,
            Optional<String> estadoUso,
            Optional<BigDecimal> precoIni,
            Optional<BigDecimal> preco,
            Optional<Boolean> aceitaTroca,
            double latitude,
            double longitude,
            Integer raio,
            Pageable pageable
    );

}
