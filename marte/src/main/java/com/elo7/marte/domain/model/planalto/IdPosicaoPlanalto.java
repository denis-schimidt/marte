package com.elo7.marte.domain.model.planalto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Preconditions;

@Embeddable
public class IdPosicaoPlanalto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long sonda;
	
	private Integer planalto; 

	IdPosicaoPlanalto() {}
	
	public IdPosicaoPlanalto(Long sonda, Integer planalto) {
		Preconditions.checkArgument(sonda !=null && planalto != null, "A sonda e o planalto não podem ser nulos.");
		
		this.sonda = sonda;
		this.planalto = planalto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(planalto,sonda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		IdPosicaoPlanalto other = (IdPosicaoPlanalto) obj;
		return Objects.equals(planalto, other.planalto) &&
				Objects.equals(sonda, other.sonda);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
