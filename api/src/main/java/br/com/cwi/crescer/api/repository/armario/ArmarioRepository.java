package br.com.cwi.crescer.api.repository.armario;

import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.dto.BuscaArmarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ArmarioRepository extends PagingAndSortingRepository<Armario, Integer> {
    Optional<Armario> findByIdArmario(Integer id);

    Page<Armario> findByNome(String nome, Pageable pagina);

    Page<Armario> findByUsuarioIdUsuario(Integer id, Pageable pagina);

    List<Armario> findByUsuarioIdUsuario(Integer id);



    @Query(value = "    SELECT DISTINCT A.ID_ARMARIO AS idArmario, A.NOME as nomeArmario , T.NOME as nomeTag , U.NOME as nomeUsuario, M.NOME as nomeCidade ,E.LATITUDE, E.LONGITUDE " +
            "           FROM ARMARIO A  " +
            "           INNER JOIN ITEM I ON A.ID_ARMARIO = I.ID_ARMARIO  " +
            "           INNER JOIN USUARIO U ON U.ID_USUARIO = A.ID_USUARIO  " +
            "           INNER JOIN ENDERECO E ON E.ID_ENDERECO = U.ID_ENDERECO " +
            "           INNER JOIN MUNICIPIO M ON M.ID_MUNICIPIO = E.ID_MUNICIPIO " +
            "           INNER JOIN TAGS T ON T.ID_TAG = A.ID_TAG  " +
            "           WHERE (SELECT COUNT(1) FROM ITEM IT WHERE IT.ID_ARMARIO = A.ID_ARMARIO AND IT.STATUS = 'D')  > 0" +
            "           AND 6378.137 * ACos ( Cos( ( ?1 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?2 )) ) + (Sin(  ( ?1 )) * Sin( (E.LATITUDE *(3.1415/180) ) )) ) < ?3 " +
            "           ORDER BY 6378.137 * ACos ( Cos( ( ?1 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?2 )) ) + (Sin(  ( ?1 )) * Sin( (E.LATITUDE *(3.1415/180) ))))"
            ,
            countQuery = "      SELECT COUNT(1)  " +
            "                   FROM ( "+
            "                   SELECT DISTINCT COUNT (1)" +
            "                   FROM ARMARIO A  " +
            "                   INNER JOIN ITEM I ON A.ID_ARMARIO = I.ID_ARMARIO  " +
            "                   INNER JOIN USUARIO U ON U.ID_USUARIO = A.ID_USUARIO  " +
            "                   INNER JOIN ENDERECO E ON E.ID_ENDERECO = U.ID_ENDERECO " +
            "                   INNER JOIN MUNICIPIO M ON M.ID_MUNICIPIO = E.ID_MUNICIPIO " +
            "                   INNER JOIN TAGS T ON T.ID_TAG = A.ID_TAG  " +
            "                   WHERE (SELECT COUNT(1) FROM ITEM IT WHERE IT.ID_ARMARIO = A.ID_ARMARIO AND IT.STATUS = 'D')  > 0" +
            "                   AND 6378.137 * ACos ( Cos( ( ?1 ) ) * Cos( (E.LATITUDE *(3.1415/180) ))* Cos( ((E.LONGITUDE*(3.1415/180) )) - (( ?2 )) ) + (Sin(  ( ?1 )) * Sin( (E.LATITUDE *(3.1415/180) ) )) ) < ?3 " +
            "                   GROUP BY A.ID_ARMARIO ) C",
            nativeQuery = true)
    Page<BuscaArmarioDto> findAllByFilter(
            double latitude,
            double longitude,
            Integer raio,
            Pageable pageable
    );


}

