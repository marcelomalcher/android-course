package br.rio.puc.lac.android.course.aidlservice;

import br.rio.puc.lac.android.course.aidlservice.Soma;

interface ISumService
{
	long soma(long a, long b);
	
	long somaP(in Soma soma);
}