package ru.vstu.clustermonitor.views.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*

import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.views.queue.QueueViewModel
import android.R.attr.data
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.design.widget.Snackbar
import ru.vstu.clustermonitor.databinding.FragmentLoginBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 *
 */
class LoginFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val viewModel = ViewModelProviders.of(activity!!)[LoginViewModel::class.java]
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel

        viewModel.result.observe(this, Observer {

            if(it == true) {
                Snackbar.make(binding.root, "Успешный вход", Snackbar.LENGTH_SHORT).show()
                listener?.onLogin(true)
            }
            else
                Snackbar.make(binding.root, "Не удалось войти", Snackbar.LENGTH_SHORT).show()
        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onLogin(success: Boolean)
    }
}
